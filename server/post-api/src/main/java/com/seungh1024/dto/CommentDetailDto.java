package com.seungh1024.dto;

/*
 * 댓글 상세 정보 DTO(게시글 클릭 후 먼저 보이는 5개의 댓글)
 *
 * @Author 강승훈
 * @Since 2023.07.30
 *
 * */

import com.fasterxml.jackson.annotation.JsonFormat;
import com.seungh1024.entity.comment.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentDetailDto {
    private final Long commentId;
    private final String commentContent;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private final LocalDateTime createdAt;

    public CommentDetailDto(Comment comment) {
        this.commentId = comment.getCommentId();
        this.commentContent = comment.getCommentContent();
        this.createdAt = comment.getCreatedAt();
    }

    public CommentDetailDto(Long comment_id, String comment_content, LocalDateTime comment_createdAt) {
        this.commentId = comment_id;
        this.commentContent = comment_content;
        this.createdAt = comment_createdAt;
    }
}
