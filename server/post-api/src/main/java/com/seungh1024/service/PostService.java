package com.seungh1024.service;

import com.seungh1024.dto.PostDto;
import com.seungh1024.dto.PostResDto;
import com.seungh1024.repository.post.condition.PostSearchConditionDto;
import com.seungh1024.repository.post.dto.PostMemberDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/*
 * PostService : Transactional Service
 *
 * @Author 강승훈
 * @Since 2023.06.27
 *
 * */
public interface PostService {
//    void createPost(PostDto postDto, Long memberId);
    List<PostResDto> getMyPosts(Long memberId);
    Page<PostMemberDto> searchPosts(PostSearchConditionDto condition, Pageable pageable);
}
