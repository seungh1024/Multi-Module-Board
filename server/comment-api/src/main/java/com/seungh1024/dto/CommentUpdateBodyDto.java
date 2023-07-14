package com.seungh1024.dto;

import com.seungh1024.entity.comment.Comment;
import com.seungh1024.repository.comment.dto.CommentUpdateDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/*
 * Comment 수정 폼
 *
 * @Author 강승훈
 * @Since 2023.07.14
 *
 * */
@Getter
@RequiredArgsConstructor
public class CommentUpdateBodyDto {
    @NotNull
    private final Long commentId;

    @NotBlank
    private final String commentContent;

    public CommentUpdateDto reqBodyToEntityDto(){
        return new CommentUpdateDto(this.commentContent);
    }
}
