package com.seungh1024.repository.comment.dto;

import com.seungh1024.entity.comment.Comment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/*
 * Comment 업데이트 DTO
 *
 * @Author 강승훈
 * @Since 2023.07.14
 *
 * */
@Getter
public class CommentUpdateDto {
    private final String commentContent;

    public CommentUpdateDto(String commentContent){
        this.commentContent = commentContent;
    }
}
