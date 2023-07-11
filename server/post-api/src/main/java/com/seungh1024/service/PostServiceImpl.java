package com.seungh1024.service;

import com.seungh1024.dto.PostResDto;
import com.seungh1024.entity.member.Member;
import com.seungh1024.entity.post.Post;
import com.seungh1024.repository.member.MemberRepository;
import com.seungh1024.repository.post.PostRepository;
import com.seungh1024.repository.post.condition.PostSearchConditionDto;
import com.seungh1024.repository.post.dto.PostMemberDto;
import lombok.RequiredArgsConstructor;
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
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;


    @Override
    @Transactional
    public List<PostResDto> getMyPosts(Long memberId) {
        List<Post> posts = postRepository.findPostByMember_MemberId(memberId);
        Member member = memberRepository.findById(memberId).get();
        String memberName = member.getMemberName();
        PostResDto postResDto = PostResDto.builder().build();
        List<PostResDto> myPosts = postResDto.entityToDto(posts,memberName);

        return myPosts;
    }

    @Override
    @Transactional
    public Page<PostMemberDto> searchPosts(PostSearchConditionDto condition, Pageable pageable) {
        return postRepository.getPostList(condition, pageable);
    }
}
