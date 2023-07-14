package com.seungh1024.repository.post.dto;

import lombok.Getter;

import java.time.LocalDateTime;

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
    private final LocalDateTime createdAt;
    private final String postContent;

    public PostDetailQueryDto(Long postId, String postName, String memberName, Integer postViews, LocalDateTime createdAt, String postContent) {
        this.postId = postId;
        this.postName = postName;
        this.memberName = memberName;
        this.postViews = postViews;
        this.createdAt = createdAt;
        this.postContent = postContent;
    }

}
