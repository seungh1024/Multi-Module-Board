package com.seungh1024.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

/*
 * Post DTO(내부 static 클래스 필요 없을듯?)
 *
 * @Author 강승훈
 * @Since 2023.06.27
 * */
@Getter
@Builder
public class PostDto {
    @NotBlank
    @Size(max = 30, message = "제목은 30자 이하로 설정해 주세요.")
    private final String postName;
    @NotBlank
    private final String postContent;

    public String toString(){
        return "postNmae: "+postName + "String: "+postContent;
    }
}
