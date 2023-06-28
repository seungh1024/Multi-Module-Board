package com.seungh1024.application;

import com.seungh1024.dto.PostDto;

/*
 * PostApplication : no transactional post service
 *
 * @Author 강승훈
 * @Since 2023.06.27
 *
 * */
public interface PostApplication {
    void createPost(PostDto postDto, String memberEmail);

}
