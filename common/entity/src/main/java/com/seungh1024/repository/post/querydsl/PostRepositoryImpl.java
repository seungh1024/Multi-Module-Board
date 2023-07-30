package com.seungh1024.repository.post.querydsl;


import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.seungh1024.entity.comment.QComment;
import com.seungh1024.entity.post.Post;
import com.seungh1024.repository.post.condition.PostDetailCondition;
import com.seungh1024.repository.post.condition.PostSearchConditionDto;
import com.seungh1024.repository.post.dto.PostDetailQueryDto;
import com.seungh1024.repository.post.dto.PostMemberQueryDto;
import com.seungh1024.repository.support.QuerydslSupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.seungh1024.entity.comment.QComment.comment;
import static com.seungh1024.entity.member.QMember.member;
import static com.seungh1024.entity.post.QPost.post;

/*
 * PostRepositoryCustom 구현체
 *
 * @Author 강승훈
 * @Since 2023.07.11
 *
 * */
public class PostRepositoryImpl extends QuerydslSupport implements PostRepositoryCustom{
    public PostRepositoryImpl(){
        super(Post.class);
    }

    
    @Override
    public Page<PostMemberQueryDto> searchPosts(PostSearchConditionDto condition, Pageable pageable) {
        return applyPagination(pageable,select(
                Projections.constructor(PostMemberQueryDto.class,
                        post.postId,
                        post.postName,
                        member.memberName,
                        post.postViews,
                        post.createdAt
                ))
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
                )
        );
    }

    @Override
    public Page<PostMemberQueryDto> getMyPosts(Long memberId, Pageable pageable) {
        return applyPagination(pageable,select(
                Projections.constructor(PostMemberQueryDto.class,
                        post.postId,
                        post.postName,
                        member.memberName,
                        post.postViews,
                        post.createdAt
                ))
                .from(post)
                .leftJoin(post.member, member)
                .where(
                        memberIdEq(memberId)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()),

                select(post.count())
                        .from(post)
                        .leftJoin(post.member, member)
                        .where(memberIdEq(memberId))
        );
    }

    @Override
    public List<PostDetailQueryDto> getPostDetails(PostDetailCondition condition) {
//        return select(
//                post
//                )
//                .from(post)
//                .join(post.member, member).fetchJoin()
//                .join(post.comments, comment).fetchJoin()
//                .where(comment.commentId.in(
//                        JPAExpressions
//                                .select(comment.commentId)
//                                .from(comment)
//                                .where(postIdEq(condition.getPostId()))
//                                .offset(0)
//                                .limit(5)
//                ),postIdEq(condition.getPostId()))
//                .fetchOne();
        // querydsl subquery limit이 동작하지 않는 이슈가 있어서 최적화가 안됨. 댓글 1000개면 다 읽어야 함


        String query = "select post.post_id, post.post_name, post.post_content, post.created_at as post_createdAt,post.post_views, " +
                "member.member_id, member.member_name, " +
                "comment.comment_id, comment.comment_content, comment.created_at as comment_createdAt\n" +
                "from post\n" +
                "join member\n" +
                "join comment\n" +
                "on comment.comment_id in(\n" +
                "    select * from(\n" +
                "        select comment.comment_id\n" +
                "        from comment\n" +
                "        where(comment.post_id = :postId)\n" +
                "        limit 0,5\n" +
                "        ) as t\n" +
                "    )\n" +
                "where post.post_id = :postId";
        List<Object[]> test = getEntityManager().createNativeQuery(query)
                .setParameter("postId",condition.getPostId())
                .getResultList();

        StringBuilder sb = new StringBuilder();
        for(Object[] o : test){
            int size = o.length;
            for(int i = 0; i < size; i++){
                sb.append(o[i]).append(", ");
            }
            sb.append("\n");
        }
        System.out.println(sb);

        List<PostDetailQueryDto> result = test.stream()
                .map(o -> new PostDetailQueryDto(o))
                .toList();
        System.out.println("===================");



//        List<PostDetailQueryDto> result = getEntityManager().createNativeQuery(query, PostDetailQueryDto.class)
//                .setParameter("postId",condition.getPostId())
//                .getResultList();
        return result;

    }


    private BooleanExpression postIdEq(Long postId){
        return post.postId.eq(postId);
    }
    private BooleanExpression memberIdEq(Long memberId){
        return post.member.memberId.eq(memberId);
    }


    private BooleanExpression postNameEq(String postName) {
        return StringUtils.hasText(postName) ? post.postName.contains(postName) : null;
    }

    private BooleanExpression memberNameEq(String memberName) {
        return StringUtils.hasText(memberName)? member.memberName.contains(memberName) : null;
    }
}
