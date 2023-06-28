package com.seungh1024.service;

import com.seungh1024.dto.PostDto;

/*
 * PostService : Transactional Service
 *
 * @Author 강승훈
 * @Since 2023.06.27
 *
 * */
public interface PostService {
    void createPost(PostDto postDto, Long memberId);
}
