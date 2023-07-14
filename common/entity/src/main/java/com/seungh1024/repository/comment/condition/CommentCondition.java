package com.seungh1024.repository.comment.condition;

import lombok.Getter;

/*
 * Comment 검색 조건(게시물 번호)
 *
 * @Author 강승훈
 * @Since 2023.07.14
 *
 * */
@Getter
public class CommentCondition {
    private final Long postId;

    public CommentCondition(Long postId){
        this.postId = postId;
    }
}
