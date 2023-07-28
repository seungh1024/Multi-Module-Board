package com.seungh1024.service;

import com.seungh1024.dto.PostDto;
import com.seungh1024.repository.post.condition.PostDetailCondition;
import com.seungh1024.repository.post.condition.PostSearchConditionDto;
import com.seungh1024.repository.post.dto.PostDetailQueryDto;
import com.seungh1024.repository.post.dto.PostMemberQueryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/*
 * PostService : Transactional Service
 *
 * @Author 강승훈
 * @Since 2023.06.27
 *
 * */
public interface PostService {
    void createPost(PostDto postDto, Long memberId);
    Page<PostMemberQueryDto> getMyPosts(Long memberId, Pageable pageable);
    Page<PostMemberQueryDto> searchPosts(PostSearchConditionDto condition, Pageable pageable);
    PostDetailQueryDto getPostDetails(Long memberId, PostDetailCondition condition);

    void modifyPost(Long memberId, PostDetailCondition condition);
}
