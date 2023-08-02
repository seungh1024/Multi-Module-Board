package com.seungh1024.application;

import com.seungh1024.dto.response.PostDetailResDto;
import com.seungh1024.dto.PostDto;
import com.seungh1024.dto.response.PostResDto;
import com.seungh1024.repository.post.condition.PostDetailCondition;
import com.seungh1024.repository.post.condition.PostSearchConditionDto;
import com.seungh1024.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/*
 * PostApplicationImpl : postApplication 구현체
 *
 * @Author 강승훈
 * @Since 2023.06.27
 *
 * */
@Service
@RequiredArgsConstructor
@Slf4j
public class PostApplicationImpl implements PostApplication{
    private final PostService postService;

    @Override
    public void createPost(PostDto postDto, Long memberId) {
        postService.createPost(postDto,memberId);
    }

    @Override
    public Page<PostResDto> getMyPosts(Long memberId, Pageable pageable) {
        return postService.getMyPosts(memberId, pageable);
    }

    @Override
    public Page<PostResDto> searchPosts(PostSearchConditionDto condition, Pageable pageable) {
        return postService.searchPosts(condition,pageable);
    }

    @Override
    public PostDetailResDto getPostDetails(Long memberId, PostDetailCondition condition) {
        return postService.getPostDetails(memberId, condition);
    }

    @Override
    public void modifyPost(Long memberId, PostDetailCondition condition) {
        postService.modifyPost(memberId, condition);
    }
}
