package com.seungh1024.repository.post.condition;

import lombok.Getter;
import lombok.ToString;

/*
 * 게시글 검색 조건 DTO
 *
 * @Author 강승훈
 * @Since 2023.07.11
 *
 * */

@Getter
@ToString
public class PostSearchConditionDto {
    // 작성자 이름, 게시글 이름
    private final String memberName;
    private final String postName;

    public PostSearchConditionDto(String memberName , String postName){
        this.memberName = memberName;
        this.postName = postName;
    }
}
