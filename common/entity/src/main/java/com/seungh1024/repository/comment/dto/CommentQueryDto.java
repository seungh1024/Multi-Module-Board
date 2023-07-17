package com.seungh1024.repository.comment.dto;

import lombok.Getter;

import java.time.LocalDateTime;

/*
 * Comment Querydsl 조회 DTO
 *
 * @Author 강승훈
 * @Since 2023.07.14
 *
 * */
@Getter
public class CommentQueryDto {
    private final Long commentId;
    private final String memberName;
    private final String commentContent;
    private final String createdAt;

    public CommentQueryDto(Long commentId, String memberName, String commentContent, String createdAt){
        this.commentId = commentId;
        this.memberName = memberName;
        this.commentContent = commentContent;
        this.createdAt = createdAt;
    }
}
