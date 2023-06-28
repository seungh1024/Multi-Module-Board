package com.seungh1024.service;

import com.seungh1024.dto.PostDto;
import com.seungh1024.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/*
 * PostServiceImpl : PostService 구현체
 *
 * @Author 강승훈
 * @Since 2023.06.27
 *
 * */
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;

    @Override
    public void createPost(PostDto postDto) {

    }
}
