package com.seungh1024.controller;

import com.seungh1024.Response;
import com.seungh1024.application.PostApplication;
import com.seungh1024.dto.PostDto;
import com.seungh1024.dto.PostResDto;
import com.seungh1024.repository.post.condition.PostSearchConditionDto;
import com.seungh1024.repository.post.dto.PostMemberDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
public class PostController {
    private final PostApplication postApplication;

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

    // 게시글 검색
    @GetMapping("/search")
    public Response<?> getSearchPosts(PostSearchConditionDto condition, Pageable pageable){
        return success(postApplication.searchPosts(condition,pageable));
    }

    //산텍힌 게시물 하나
    @GetMapping("/detail")
    public Response<?> getPost(@AuthenticationPrincipal Long memberId, Long postId){
        return success(postApplication.getPostDetails(memberId, postId));
    }

    //게시글 수정. 내 것만 가능하도록
    @PatchMapping()
    public Response<?> updatePosts(){
        return success();
    }
}
