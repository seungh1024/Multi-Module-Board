package com.seungh1024.application;

import com.seungh1024.dto.PostDto;
import com.seungh1024.entity.member.Member;
import com.seungh1024.entity.post.Post;
import com.seungh1024.repository.member.MemberRepository;
import com.seungh1024.repository.post.PostRepository;
import com.seungh1024.repository.post.condition.PostDetailCondition;
import com.seungh1024.repository.post.condition.PostSearchConditionDto;
import com.seungh1024.repository.post.dto.PostDetailQueryDto;
import com.seungh1024.repository.post.dto.PostMemberQueryDto;
import com.seungh1024.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
        post.updateMember(member); // 양방향 모두 객체를 저장하면 쿼리가 두 번 나감. 사용자 추가 후 프록시에서 호출할 일 없으니 단방향으로만 저장
        postRepository.save(post);
    }

    @Override
    public Page<PostMemberQueryDto> getMyPosts(Long memberId, Pageable pageable) {
        return postService.getMyPosts(memberId, pageable);
    }

    @Override
    public Page<PostMemberQueryDto> searchPosts(PostSearchConditionDto condition, Pageable pageable) {
        return postService.searchPosts(condition,pageable);
    }

    @Override
    public PostDetailQueryDto getPostDetails(Long memberId, PostDetailCondition condition) {
        return postService.getPostDetails(memberId, condition);
    }

    @Override
    public void modifyPost(Long memberId, PostDetailCondition condition) {
        postService.modifyPost(memberId, condition);
    }
}
