package com.seungh1024.repository.support;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.querydsl.SimpleEntityPathResolver;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.Assert;

/*
 * querydsl support 클래스
 * 페이징, 카운터 쿼리 분리 및 Spring Data Sort 사용
 *
 * @Author 강승훈
 * @Since 2023.07.11
 *
 * */
public class QuerydslSupport {
    private final Class domainClass;
    private Querydsl querydsl;
    private EntityManager entityManager;
    private JPAQueryFactory queryFactory;

    public QuerydslSupport(Class domainClass){
        this.domainClass = domainClass;
    }
    @Autowired
    public void setEntityManager(EntityManager entityManager){
        JpaEntityInformation entityInformation = JpaEntityInformationSupport.getEntityInformation(domainClass, entityManager);
        SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
        EntityPath path = resolver.createPath(entityInformation.getJavaType());
        this.entityManager = entityManager;
        this.querydsl = new Querydsl(entityManager, new PathBuilder<>(path.getType(),path.getMetadata()));
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @PostConstruct
    public void validate() {
        Assert.notNull(entityManager, "EntityManager must not be null!");
        Assert.notNull(querydsl, "Querydsl must not be null!");
        Assert.notNull(queryFactory, "QueryFactory must not be null!");
    }

    protected JPAQueryFactory getQueryFactory(){
        return queryFactory;
    }

    protected Querydsl getQuerydsl(){
        return querydsl;
    }

    protected EntityManager getEntityManager(){
        return entityManager;
    }

    protected <T> JPAQuery<T> select(Expression<T> expr){
        return getQueryFactory().select(expr);
    }
    protected <T> JPAQuery<Tuple> select(Expression<?>... expr){
        return getQueryFactory().select(expr);
    }

    protected  <T> JPAQuery<T> selectFrom(EntityPath<T> from){
        return getQueryFactory().selectFrom(from);
    }
    protected <T> JPAUpdateClause update(EntityPath<T> update){
        return getQueryFactory().update(update);
    }

    protected <T> Page<T> applyPagination(Pageable pageable, JPAQuery contentQuery, JPAQuery<Long> countQuery){
        return PageableExecutionUtils.getPage(contentQuery.fetch(), pageable, countQuery::fetchOne);
    }
}
