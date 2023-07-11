package com.seungh1024.repository.post.querydsl;


import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.seungh1024.entity.post.Post;
import com.seungh1024.repository.post.condition.PostSearchConditionDto;
import com.seungh1024.repository.post.dto.PostMemberDto;
import com.seungh1024.repository.support.QuerydslSupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.seungh1024.entity.member.QMember.member;
import static com.seungh1024.entity.post.QPost.post;

/*
 * PostRepositoryCustom 구현체
 *
 * @Author 강승훈
 * @Since 2023.07.11
 *
 * */
//@Repository
public class PostRepositoryImpl extends QuerydslSupport implements PostRepositoryCustom{
    public PostRepositoryImpl(){
        super(Post.class);
    }

    
    @Override
    public Page<PostMemberDto> getPostList(PostSearchConditionDto condition, Pageable pageable) {
        return applyPagination(pageable,select(
                        Projections.constructor(PostMemberDto.class,
                                post.postName,
                                member.memberName,
                                post.postViews,
                                post.createdAt
                        )
                )
                .from(post)
                .leftJoin(post.member, member)
                .where(
                        postNameEq(condition.getPostName()),
                        memberNameEq(condition.getMemberName())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()),
                select(post.count())
                .from(post)
                .leftJoin(post.member, member)
                .where(
                        postNameEq(condition.getPostName()),
                        memberNameEq(condition.getMemberName())
                ));
    }


    private BooleanExpression postNameEq(String postName) {
        return StringUtils.hasText(postName) ? post.postName.contains(postName) : null;
    }

    private BooleanExpression memberNameEq(String memberName) {
        return StringUtils.hasText(memberName)? member.memberName.contains(memberName) : null;
    }
}
