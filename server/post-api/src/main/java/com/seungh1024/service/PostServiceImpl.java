package com.seungh1024.service;

import com.seungh1024.custom.InvalidMemberException;
import com.seungh1024.dto.response.PostDetailResDto;
import com.seungh1024.dto.PostDto;
import com.seungh1024.dto.response.PostResDto;
import com.seungh1024.entity.member.Member;
import com.seungh1024.entity.post.Post;
import com.seungh1024.exception.custom.PostNotFoundException;
import com.seungh1024.repository.member.MemberRepository;
import com.seungh1024.repository.post.PostRepository;
import com.seungh1024.repository.post.condition.PostDetailCondition;
import com.seungh1024.repository.post.condition.PostSearchConditionDto;
import com.seungh1024.repository.post.dto.PostDetailQueryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
 * PostServiceImpl : PostService 구현체
 *
 * @Author 강승훈
 * @Since 2023.06.27
 *
 * */
@Service
@RequiredArgsConstructor
@Slf4j
//@Transactional(readOnly = true)
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;


    @Override
    @Transactional
    public void createPost(PostDto postDto, Long memberId) {
        Post post = postDto.toEntity();

        Member member = memberRepository.getReferenceById(memberId);
        post.updateMember(member);
        postRepository.save(post);
    }

    @Override
    public Page<PostResDto> getMyPosts(Long memberId, Pageable pageable) {
        return postRepository.getMyPosts(memberId,pageable)
                .map(post -> new PostResDto(post));
    }

    @Override
    public Page<PostResDto> searchPosts(PostSearchConditionDto condition, Pageable pageable) {
        return postRepository.searchPosts(condition,pageable)
                .map(post -> new PostResDto(post));
    }

    @Override
    @Transactional(readOnly = false)
    public PostDetailResDto getPostDetails(Long memberId, PostDetailCondition condition) {
        List<PostDetailQueryDto> selectPost = postRepository.getPostDetails(condition);
        for(PostDetailQueryDto dt : selectPost){
            System.out.println(dt.getPostId());
        }
        if(selectPost.isEmpty()) throw new PostNotFoundException();
        PostDetailResDto postDetailDto = new PostDetailResDto((selectPost));
        Post post = postRepository.getReferenceById(postDetailDto.getPostId());
        if(!post.isOwner(memberId)){ // 자신의 게시물이 아니면 조회수 증가
            post.updateViews();
        }
        postRepository.save(post);
        return postDetailDto;
    }

    @Override
    @Transactional(readOnly = false)
    public void modifyPost(Long memberId, PostDetailCondition condition) {
        List<PostDetailQueryDto> selectPost = postRepository.getPostDetails(condition);
        if(selectPost.isEmpty()) throw new PostNotFoundException();
        PostDetailResDto postDetailDto = new PostDetailResDto((selectPost));
        Post post = postRepository.findById(postDetailDto.getPostId()).get();
        if(!post.isOwner(memberId)) {
            throw new InvalidMemberException("권한이 없는 사용자입니다.");
        }
        post.updatePost(condition.getPostName(), condition.getPostContent());
        postRepository.save(post);
    }

}
