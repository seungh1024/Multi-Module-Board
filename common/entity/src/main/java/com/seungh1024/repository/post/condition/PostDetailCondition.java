package com.seungh1024.repository.post.condition;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/*
 * 게시글 업데이트 조건 DTo
 *
 * @Author 강승훈
 * @Since 2023.07.12
 *
 * */
@Getter
@ToString
public class PostDetailCondition {
    @NotNull
    private final Long postId;
    private final String postName;
    private final String postContent;

    public PostDetailCondition(Long postId, String postName, String postContent){
        this.postId = postId;
        this.postName = postName;
        this.postContent = postContent;
    }
}
