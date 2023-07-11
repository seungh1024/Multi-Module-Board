package com.seungh1024.application;

import com.seungh1024.dto.PostDto;
import com.seungh1024.dto.PostResDto;
import com.seungh1024.entity.member.Member;
import com.seungh1024.entity.post.Post;
import com.seungh1024.repository.member.MemberRepository;
import com.seungh1024.repository.post.PostRepository;
import com.seungh1024.repository.post.condition.PostSearchConditionDto;
import com.seungh1024.repository.post.dto.PostDetailDto;
import com.seungh1024.repository.post.dto.PostMemberDto;
import com.seungh1024.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * PostApplicationImpl : postApplication 구현체
 *
 * @Author 강승훈
 * @Since 2023.06.27
 *
 * */
@Service
@RequiredArgsConstructor
public class PostApplicationImpl implements PostApplication{
    private final PostService postService;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Override
    public void createPost(PostDto postDto, Long memberId) {
        String postName = postDto.getPostName();
        String postContent = postDto.getPostContent();
        Post post = Post.createPost(postName, postContent);

        Member member = memberRepository.getReferenceById(memberId);
        post.updateMember(member);
        postRepository.save(post);
    }

    @Override
    public Page<PostMemberDto> getMyPosts(Long memberId, Pageable pageable) {
        return postService.getMyPosts(memberId, pageable);
    }

    @Override
    public Page<PostMemberDto> searchPosts(PostSearchConditionDto condition, Pageable pageable) {
        return postService.searchPosts(condition,pageable);
    }

    @Override
    public PostDetailDto getPostDetails(Long memberId, Long postId) {
        return postService.getPostDetails(memberId, postId);
    }
}
