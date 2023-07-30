package com.seungh1024.repository.post.querydsl;

/*
 * Post querydsl custom interface
 *
 * @Author 강승훈
 * @Since 2023.07.11
 *
 * */

import com.seungh1024.entity.post.Post;
import com.seungh1024.repository.post.condition.PostDetailCondition;
import com.seungh1024.repository.post.condition.PostSearchConditionDto;
import com.seungh1024.repository.post.dto.PostDetailQueryDto;
import com.seungh1024.repository.post.dto.PostMemberQueryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostRepositoryCustom {
    Page<PostMemberQueryDto> searchPosts(PostSearchConditionDto condition, Pageable pageable);
    Page<PostMemberQueryDto> getMyPosts(Long memberId, Pageable pageable);
    List<PostDetailQueryDto> getPostDetails(PostDetailCondition condition);
}
