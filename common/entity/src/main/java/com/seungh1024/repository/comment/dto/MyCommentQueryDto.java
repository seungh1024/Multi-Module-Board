package com.seungh1024.repository.comment.dto;

import lombok.Getter;

import java.time.LocalDateTime;

/*
 * 내가 작성한 댓글 Querydsl 조회
 *
 * @Author 강승훈
 * @Since 2023.07.17
 *
 * */
@Getter
public class MyCommentQueryDto {
    private final Long postId;
    private final String postName;
    private final Long commentId;
    private final String memberName;
    private final String commentContent;
    private final String createdAt;

    public MyCommentQueryDto(Long postId, String postName, Long commentId, String memberName, String commentContent, String createdAt){
        this.postId = postId;
        this.postName = postName;
        this.commentId = commentId;
        this.memberName = memberName;
        this.commentContent = commentContent;
        this.createdAt = createdAt;
    }
}