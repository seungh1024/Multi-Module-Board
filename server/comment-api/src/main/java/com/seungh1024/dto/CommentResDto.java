package com.seungh1024.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.seungh1024.entity.comment.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

import static com.seungh1024.time.FormatUtils.BASIC_TIME_PATTERN;

/**
 * Comment Response DTO
 */
@Getter
public class CommentResDto {
    private final Long commentId;
    private final Long postId;
    private final Long memberId;
    private final String memberName;
    private final String commentContent;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = BASIC_TIME_PATTERN)
    private final LocalDateTime createdAt;

    public CommentResDto(Comment comment){
        this.commentId = comment.getCommentId();
        this.postId = comment.getPost().getPostId();
        this.memberId = comment.getMember().getMemberId();
        this.memberName = comment.getMember().getMemberName();
        this.commentContent = comment.getCommentContent();
        this.createdAt = comment.getCreatedAt();
    }
}
