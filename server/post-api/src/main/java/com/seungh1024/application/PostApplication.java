package com.seungh1024.application;

import com.seungh1024.dto.response.PostDetailResDto;
import com.seungh1024.dto.PostDto;
import com.seungh1024.dto.response.PostResDto;
import com.seungh1024.repository.post.condition.PostDetailCondition;
import com.seungh1024.repository.post.condition.PostSearchConditionDto;
import com.seungh1024.repository.post.dto.PostMemberQueryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/*
 * PostApplication : no transactional post service
 *
 * @Author 강승훈
 * @Since 2023.06.27
 *
 * */
public interface PostApplication {
    void createPost(PostDto postDto, Long memberId);
    Page<PostMemberQueryDto> getMyPosts(Long memberId, Pageable pageable);
    Page<PostResDto> searchPosts(PostSearchConditionDto condition, Pageable pageable);
    PostDetailResDto getPostDetails(Long memberId, PostDetailCondition condition);
    void modifyPost(Long memberId, PostDetailCondition condition);
}
