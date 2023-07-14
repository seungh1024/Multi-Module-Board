package com.seungh1024.dto;

import com.seungh1024.entity.comment.Comment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/*
 * Comment 생성 폼
 *
 * @Author 강승훈
 * @Since 2023.07.14
 *
 * */
@Getter
@RequiredArgsConstructor
public class CommentCreateBodyDto {
    @NotNull
    private final Long postId;
    @NotBlank
    private final String commentContent;

    public Comment dtoToEntity() {
        return new Comment(this.commentContent);
    }
}
