package com.seungh1024.repository.post.querydsl;


import com.querydsl.core.types.dsl.BooleanExpression;
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
    @Deprecated
    public List<PostDetailQueryDto> getPostDetails(PostDetailCondition condition) {
        String query = "select p.post_id, p.post_name, p.post_content, p.created_at,p.post_views," +
                "pm.member_id as post_member_id ,pm.member_name as post_member_name," +
                "c.comment_id, c.comment_content, c.created_at," +
                "cm.member_id , cm.member_name " +
                "from post p " +
                "join member pm " +
                "   on p.member_id = pm.member_id" +
                "   and p.post_id =:postId " +
                "left join comment c " +
                "join member cm " +
                "   on c.member_id = cm.member_id " +
                "   and c.comment_id in (select *\n" +
                "                         from (select cc.comment_id\n" +
                "                               from comment cc\n" +
                "                               where (cc.post_id = 1)\n" +
                "                               limit 0,10) as cl)\n" +
                "    on p.post_id =:postId";


        List<Object[]> test = getEntityManager().createNativeQuery(query)
                .setParameter("postId",condition.getPostId())
                .getResultList();


        List<PostDetailQueryDto> result = test.stream()
                .map(o -> new PostDetailQueryDto(o))
                .toList();

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
