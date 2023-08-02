package com.seungh1024.repository.comment.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.seungh1024.entity.comment.Comment;
import com.seungh1024.repository.comment.condition.CommentCondition;
import com.seungh1024.repository.support.QuerydslSupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static com.seungh1024.entity.comment.QComment.comment;
import static com.seungh1024.entity.member.QMember.member;
import static com.seungh1024.entity.post.QPost.post;

/*
 * Comment Querydsl Custom 구현체
 *
 * @Author 강승훈
 * @Since 2023.07.14
 *
 * */
public class CommentRepositoryImpl extends QuerydslSupport implements CommentRepositoryCustom{
    public CommentRepositoryImpl() {
        super(Comment.class);
    }

    public Page<Comment> getCommentList(CommentCondition condition, Pageable pageable){
        return applyPagination(pageable,
                select(comment)
                        .from(comment)
                        .join(comment.post, post).fetchJoin()
                        .join(comment.member, member).fetchJoin()
                        .where(postIdEq(condition.getPostId()))
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize()),

                select(comment.count())
                        .from(comment)
                        .join(comment.post, post)
                        .join(comment.member, member)
                        .where(postIdEq(condition.getPostId()))
        );
    }

    @Override
    public Page<Comment> getMyCommentList(Long memberId, Pageable pageable) {
        return applyPagination(pageable,
                select(comment)
                        .from(comment)
                        .join(comment.post, post).fetchJoin()
                        .join(comment.member, member).fetchJoin()
                        .where(memberIdEq(memberId))
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize()),

                select(comment.count())
                        .from(comment)
                        .join(comment.post, post)
                        .join(comment.member, member)
                        .where(memberIdEq(memberId))
        );
    }


    private BooleanExpression postIdEq(Long postId){
        return comment.post.postId.eq(postId);
    }
    private BooleanExpression memberIdEq(Long memberId){ return comment.member.memberId.eq(memberId);}
}
