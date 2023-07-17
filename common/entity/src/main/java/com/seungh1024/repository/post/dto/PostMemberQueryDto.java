package com.seungh1024.repository.post.dto;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

/*
 * 게시글 목록 DTO
 *
 * @Author 강승훈
 * @Since 2023.07.11
 *
 * */
@Getter
@ToString
public class PostMemberQueryDto {
    private final Long postId;
    private final String postName;
    private final String memberName;
    private final Integer postViews;
    private final String createdAt;

    public PostMemberQueryDto(Long postId, String postName, String memberName, Integer postViews, String createdAt){
        this.postId = postId;
        this.postName = postName;
        this.createdAt = createdAt;
        this.memberName = memberName;
        this.postViews = postViews;
    }
}

