package com.seungh1024.controller;

import com.seungh1024.Response;
import com.seungh1024.application.PostApplication;
import com.seungh1024.dto.response.PostDetailResDto;
import com.seungh1024.dto.PostDto;
import com.seungh1024.dto.response.PostResDto;
import com.seungh1024.entity.post.Post;
import com.seungh1024.repository.post.condition.PostDetailCondition;
import com.seungh1024.repository.post.condition.PostSearchConditionDto;
import com.seungh1024.repository.post.dto.PostDetailQueryDto;
import com.seungh1024.repository.post.querydsl.PostRepositoryImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.seungh1024.Response.success;

/*
 * PostController : Post CRUD
 *
 * @Author 강승훈
 * @Since 2023.06.27
 *
 * */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/post")
@Slf4j
public class PostController {
    private final PostApplication postApplication;
    private final PostRepositoryImpl postRepository;

    @PostMapping("/create")
    public Response<?> createPost(@Valid @RequestBody PostDto postDto, Authentication authentication){
        Long memberId = Long.parseLong(authentication.getName());
        postApplication.createPost(postDto,memberId);
        return success();
    }

    // 내 게시물 리스트
    @GetMapping("/my-post")
    public Response<?> getMyPosts(@AuthenticationPrincipal Long memberId, Pageable pageable){
        return success(postApplication.getMyPosts(memberId, pageable));
    }
// TODO dto 분리
    // 게시글 검색
    @GetMapping("/search")
    public Response<?> searchPosts(PostSearchConditionDto condition, Pageable pageable){
        return success(postApplication.searchPosts(condition,pageable));
    }

    //산텍힌 게시물 하나
    @GetMapping("/detail")
    public Response<?> getPost(@AuthenticationPrincipal Long memberId, PostDetailCondition condition){
        return success(postApplication.getPostDetails(memberId, condition));
    }

    //게시글 수정. 내 것만 가능하도록
    @PatchMapping("/modify")
    public Response<?> updatePosts(@AuthenticationPrincipal Long memberId, @RequestBody @Valid PostDetailCondition condition){
        postApplication.modifyPost(memberId, condition);
        return success();
    }

}
