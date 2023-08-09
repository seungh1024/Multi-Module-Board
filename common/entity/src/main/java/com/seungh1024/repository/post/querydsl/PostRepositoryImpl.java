package com.seungh1024.repository.post.querydsl;


import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.seungh1024.entity.comment.QComment;
import com.seungh1024.entity.member.QMember;
import com.seungh1024.entity.post.Post;
import com.seungh1024.repository.post.condition.PostDetailCondition;
import com.seungh1024.repository.post.condition.PostSearchConditionDto;
import com.seungh1024.repository.post.dto.PostDetailQueryDto;
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
    public Page<Post> searchPosts(PostSearchConditionDto condition, Pageable pageable) {
        return applyPagination(pageable,select(
                    post
                )
                .from(post)
                .leftJoin(post.member, member).fetchJoin()
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
    public Page<Post> getMyPosts(Long memberId, Pageable pageable) {
        return applyPagination(pageable,
                select(post)
                .from(post)
                .leftJoin(post.member, member).fetchJoin()
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
    public Post detailTestV3(PostDetailCondition condition){
        return select(
                post
        )
                .from(post)
                .join(post.member, member).fetchJoin()
                .leftJoin(post.comments, comment).fetchJoin()
                .where(postIdEq(condition.getPostId()))
                .fetchOne();
    }

    public Post detailTestV1(PostDetailCondition condition){
        List<Long> commentIdList = select(comment.commentId)
                .from(comment)
                .where(postIdEq(condition.getPostId()))
                .offset(0)
                .limit(10)
                .fetch();

        return select(
                post
        )
                .from(post)
                .join(post.member, member).fetchJoin()
                .leftJoin(post.comments, comment).fetchJoin()
                .where(comment.commentId.in(commentIdList),postIdEq(condition.getPostId()))
                .fetchOne();
    }

    //TODO Jdbc로 수정됨
//    @Override
//    @Deprecated
    public List<PostDetailQueryDto> getPostDetailsOrigin(PostDetailCondition condition) {
        QMember commentMember = new QMember("commentMember");
        return select(Projections.constructor(PostDetailQueryDto.class,
                    post.postId,
                    post.postName,
                    post.postContent,
                    post.createdAt,
                    post.postViews,
                    member.memberId,
                    member.memberName,
                    comment.commentId,
                    comment.commentContent,
                    comment.createdAt,
                    commentMember.memberId,
                    commentMember.memberName
                ))
                .from(post)
                .join(post.member, member)
                .on(postIdEq(condition.getPostId()))
                .leftJoin(post.comments, comment)
                .on(comment.post.postId.eq(post.postId))
                    .join(comment.member, commentMember)
                .on(comment.post.postId.eq(condition.getPostId()))
                .orderBy(comment.commentId.asc())
                .offset(0)
                .limit(10)
                .fetch();
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
