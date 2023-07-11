package com.seungh1024.controller;

import com.seungh1024.Response;
import com.seungh1024.application.PostApplication;
import com.seungh1024.dto.PostDto;
import com.seungh1024.dto.PostResDto;
import com.seungh1024.repository.post.condition.PostSearchConditionDto;
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
    public Response<?> getMyPosts(@AuthenticationPrincipal Long memberId){
        List<PostResDto> myPosts = postApplication.getMyPosts(memberId);
        return success(myPosts);
    }

    // 선택한 게시물
    @GetMapping("/{postNumber}")
    public Response<?> getPost(){
        return success();
    }

    // 게시글 검색
    @GetMapping("/search")
    public Page<?> getSearchPosts(PostSearchConditionDto condition, Pageable pageable){
        return postApplication.searchPosts(condition,pageable);
    }

    //게시글 수정. 내 것만 가능
    @PatchMapping()
    public Response<?> updatePosts(){
        return success();
    }
}
