package com.seungh1024.application;

import com.seungh1024.dto.PostDto;
import com.seungh1024.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/*
 * PostApplicationImpl : postApplication 구현체
 *
 * @Author 강승훈
 * @Since 2023.06.27
 *
 * */
@Service
@RequiredArgsConstructor
public class PostApplicationImpl implements PostApplication{
    private final PostService postService;

    @Override
    public void createPost(PostDto postDto) {
        postService.createPost(postDto);
    }
}
