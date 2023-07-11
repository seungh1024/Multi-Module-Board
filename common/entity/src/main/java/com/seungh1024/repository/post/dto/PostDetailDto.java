package com.seungh1024.repository.post.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostDetailDto {
    private final Long postId;
    private final String postName;
    private final String memberName;
    private final Integer postViews;
    private final LocalDateTime createdAt;
    private final String postContent;

    public PostDetailDto(Long postId, String postName, String memberName, Integer postViews, LocalDateTime createdAt, String postContent) {
        this.postId = postId;
        this.postName = postName;
        this.memberName = memberName;
        this.postViews = postViews;
        this.createdAt = createdAt;
        this.postContent = postContent;
    }
}
