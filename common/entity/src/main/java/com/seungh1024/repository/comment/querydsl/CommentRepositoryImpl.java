package com.seungh1024.repository.comment.querydsl;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.seungh1024.entity.comment.Comment;
import com.seungh1024.entity.comment.QComment;
import com.seungh1024.entity.member.QMember;
import com.seungh1024.entity.post.QPost;
import com.seungh1024.repository.comment.condition.CommentCondition;
import com.seungh1024.repository.comment.dto.CommentQueryDto;
import com.seungh1024.repository.comment.dto.MyCommentQueryDto;
import com.seungh1024.repository.support.QuerydslSupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

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

    public Page<CommentQueryDto> getCommentList(CommentCondition condition, Pageable pageable){
        return applyPagination(pageable,
                select(
                        Projections.constructor(CommentQueryDto.class,
                        comment.commentId,
                        comment.member.memberName,
                        comment.commentContent,
                        comment.post.createdAt
                        )
                )
                        .from(comment)
                        .leftJoin(comment.post, post)
                        .leftJoin(comment.member, member)
                        .where(postIdEq(condition.getPostId()))
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize()),

                select(comment.count())
                        .from(comment)
                        .leftJoin(comment.post, post)
                        .leftJoin(comment.member, member)
                        .where(postIdEq(condition.getPostId()))
        );
    }

    @Override
    public Page<MyCommentQueryDto> getMyCommentList(Long memberId, Pageable pageable) {
        return applyPagination(pageable,
                select(
                        Projections.constructor(MyCommentQueryDto.class,
                                comment.post.postId,
                                comment.post.postName,
                                comment.commentId,
                                comment.member.memberName,
                                comment.commentContent,
                                comment.createdAt
                                )
                )
                        .from(comment)
                        .leftJoin(comment.post, post)
                        .leftJoin(comment.member, member)
                        .where(memberIdEq(memberId))
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize()),

                select(comment.count())
                        .from(comment)
                        .leftJoin(comment.post, post)
                        .leftJoin(comment.member, member)
                        .where(memberIdEq(memberId))
        );
    }


    private BooleanExpression postIdEq(Long postId){
        return comment.post.postId.eq(postId);
    }
    private BooleanExpression memberIdEq(Long memberId){ return comment.member.memberId.eq(memberId);}
}
