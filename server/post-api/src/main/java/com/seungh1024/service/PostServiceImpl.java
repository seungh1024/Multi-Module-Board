package com.seungh1024.service;

import com.seungh1024.dto.PostDto;
import com.seungh1024.member.Member;
import com.seungh1024.member.Post;
import com.seungh1024.repository.MemberRepository;
import com.seungh1024.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public void createPost(PostDto postDto, Long memberId) {
        String postName = postDto.getPostName();
        String postContent = postDto.getPostContent();
        Post post = Post.createPost(postName, postContent);

        Member member = memberRepository.getReferenceById(memberId);
        post.updateMember(member);
        postRepository.save(post);

    }
}
