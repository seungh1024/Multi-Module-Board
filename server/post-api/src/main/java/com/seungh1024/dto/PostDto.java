package com.seungh1024.dto;

import lombok.Builder;
import lombok.Getter;

/*
 * Post DTO(내부 static 클래스 필요 없을듯?)
 *
 * @Author 강승훈
 * @Since 2023.03.20
 * */
@Getter
@Builder
public class PostDto {
    private final String postName;
    private final String postContent;
}
