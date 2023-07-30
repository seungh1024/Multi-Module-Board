package com.seungh1024.repository.comment.dto;

import com.seungh1024.repository.base.BaseTime;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/*
 * Comment Querydsl 조회 DTO
 *
 * @Author 강승훈
 * @Since 2023.07.14
 *
 * */
@Getter
public class CommentQueryDto extends BaseTime {
    private final Long commentId;
    private final String memberName;
    private final String commentContent;


    public CommentQueryDto(Long commentId, String memberName, String commentContent, LocalDateTime createdAt){
        super(createdAt);
        this.commentId = commentId;
        this.memberName = memberName;
        this.commentContent = commentContent;
    }
}
