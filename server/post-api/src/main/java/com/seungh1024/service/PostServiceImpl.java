package com.seungh1024.service;

import com.seungh1024.custom.InvalidMemberException;
import com.seungh1024.entity.post.Post;
import com.seungh1024.repository.post.PostRepository;
import com.seungh1024.repository.post.condition.PostDetailCondition;
import com.seungh1024.repository.post.condition.PostSearchConditionDto;
import com.seungh1024.repository.post.dto.PostDetailQueryDto;
import com.seungh1024.repository.post.dto.PostMemberQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public Page<PostMemberQueryDto> getMyPosts(Long memberId, Pageable pageable) {
        return postRepository.getMyPosts(memberId,pageable);
    }

    @Override
    @Transactional
    public Page<PostMemberQueryDto> searchPosts(PostSearchConditionDto condition, Pageable pageable) {
        return postRepository.searchPosts(condition, pageable);
    }

    @Override
    @Transactional
    public PostDetailQueryDto getPostDetails(Long memberId, PostDetailCondition condition) {
        Post selectPost = postRepository.getPostDetails(condition);
        if(selectPost == null) throw new NullPointerException();
        if(!selectPost.isOwner(memberId)){ // 자신의 게시물이 아니면 조회수 증가
            selectPost.updateViews();
        }
        postRepository.save(selectPost);
        return selectPost.entityToDto();
    }

    @Override
    @Transactional
    public void modifyPost(Long memberId, PostDetailCondition condition) {
        Post selectPost = postRepository.getPostDetails(condition);
        if(selectPost == null) throw new NullPointerException();
        if(!selectPost.isOwner(memberId)) {
            throw new InvalidMemberException("권한이 없는 사용자입니다.");
        }
        selectPost.updatePost(condition);
        postRepository.save(selectPost);
    }

}
