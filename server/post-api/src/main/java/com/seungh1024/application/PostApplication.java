package com.seungh1024.application;

import com.seungh1024.dto.PostDto;
import com.seungh1024.dto.PostResDto;
import com.seungh1024.repository.post.condition.PostSearchConditionDto;
import com.seungh1024.repository.post.dto.PostDetailDto;
import com.seungh1024.repository.post.dto.PostMemberDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/*
 * PostApplication : no transactional post service
 *
 * @Author 강승훈
 * @Since 2023.06.27
 *
 * */
public interface PostApplication {
    void createPost(PostDto postDto, Long memberId);
    Page<PostMemberDto> getMyPosts(Long memberId, Pageable pageable);
    Page<PostMemberDto> searchPosts(PostSearchConditionDto condition, Pageable pageable);
    PostDetailDto getPostDetails(Long memberId, Long postId);
}
