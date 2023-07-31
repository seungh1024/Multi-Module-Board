package com.seungh1024.dto.response;

/*
 * 댓글 상세 정보 DTO(게시글 클릭 후 먼저 보이는 5개의 댓글)
 *
 * @Author 강승훈
 * @Since 2023.07.30
 *
 * */

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

import static com.seungh1024.time.FormatUtils.BASIC_TIME_PATTERN;

@Getter
public class CommentDetailResDto {
    private final Long commentId;
    private final String commentContent;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = BASIC_TIME_PATTERN)
    private final LocalDateTime createdAt;
    private final Long memberId;
    private final String memberName;


    public CommentDetailResDto(Long comment_id, String comment_content, LocalDateTime comment_createdAt, Long commentMemberId, String commentMemberName) {
        this.commentId = comment_id;
        this.commentContent = comment_content;
        this.createdAt = comment_createdAt;
        this.memberId = commentMemberId;
        this.memberName = commentMemberName;
    }
}
