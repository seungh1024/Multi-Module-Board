package com.seungh1024.repository.post.dto;

import com.seungh1024.entity.post.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * 게시글 상세 정보 DTO(게시글 클릭 후 보이는 정보)
 *
 * @Author 강승훈
 * @Since 2023.07.12
 *
 * */
@Getter
public class PostDetailQueryDto {
    private final Long postId;
    private final String postName;
    private final String memberName;
    private final Integer postViews;
    private final String createdAt;
    private final String postContent;

    public PostDetailQueryDto(Long postId, String postName, String memberName, Integer postViews, String createdAt, String postContent) {
        this.postId = postId;
        this.postName = postName;
        this.memberName = memberName;
        this.postViews = postViews;
        this.createdAt = createdAt;
        this.postContent = postContent;
    }

    public PostDetailQueryDto(Post selectPost) {
        this.postId = selectPost.getPostId();
        this.postName = selectPost.getPostName();
        this.memberName = selectPost.getMember().getMemberName();
        this.postViews = selectPost.getPostViews();
        this.createdAt = selectPost.getCreatedAt().format(DateTimeFormatter.ofPattern("yy.MM.dd HH:mm"));
        this.postContent = selectPost.getPostContent();
    }
}
