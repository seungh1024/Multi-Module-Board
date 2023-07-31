package com.seungh1024.controller;

import com.seungh1024.entity.comment.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentDetailV1 {
    private Long commentId;
    private String commentContent;
    private Long memberId;
    private String memberName;
    private LocalDateTime createdAt;

    public CommentDetailV1(Comment comment) {
        this.commentId = comment.getCommentId();
        this.commentContent = comment.getCommentContent();
        this.memberId = comment.getMember().getMemberId();
        this.memberName = comment.getMember().getMemberName();
        this.createdAt = comment.getCreatedAt();
    }
}
