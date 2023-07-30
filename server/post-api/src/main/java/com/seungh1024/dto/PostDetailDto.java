package com.seungh1024.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.seungh1024.entity.post.Post;
import com.seungh1024.repository.post.dto.PostDetailQueryDto;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

/*
 * 게시글 상세 정보 DTO(응답용)
 *
 * @Author 강승훈
 * @Since 2023.07.30
 *
 * */

@Getter
public class PostDetailDto {
    private final Long postId;
    private final String postName;
    private final Long memberId;
    private final String memberName;
    private final Integer postViews;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private final LocalDateTime createdAt;
    private final String postContent;
    private final List<CommentDetailDto> commentList;

    public PostDetailDto(Post post) {
        this.postId = post.getPostId();
        this.postName = post.getPostName();
        this.memberId = post.getMember().getMemberId();
        this.memberName = post.getMember().getMemberName();
        this.postViews = post.getPostViews();
        this.createdAt = post.getCreatedAt();
        this.postContent = post.getPostContent();
        this.commentList = post.getComments().stream()
                .map(comment -> new CommentDetailDto(comment))
                .toList();
    }

    public PostDetailDto(List<PostDetailQueryDto> selectPost) {
        PostDetailQueryDto postDetail = selectPost.get(0);
        this.postId = postDetail.getPost_id();
        this.postName = postDetail.getPost_name();
        this.memberId = postDetail.getMember_id();
        this.memberName = postDetail.getMember_name();
        this.postViews = postDetail.getPost_views();
        this.createdAt = postDetail.getPost_createdAt();
        this.postContent = postDetail.getPost_content();
        this.commentList = selectPost.stream()
                .map(comment -> new CommentDetailDto(comment.getComment_id(),comment.getComment_content(), comment.getComment_createdAt()))
                .toList();
    }
}
