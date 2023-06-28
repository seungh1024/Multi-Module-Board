package com.seungh1024.controller;

import com.seungh1024.Response;
import com.seungh1024.application.PostApplication;
import com.seungh1024.dto.PostDto;
import com.seungh1024.member.Post;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
        String memberEmail = authentication.getName();
        postApplication.createPost(postDto,memberEmail);
        return success();
    }

    // 내 게시물 리스트
    @GetMapping("/my-post")
    public Response<?> getMyPosts(){
        Post myPost = new Post();
        return success(myPost);
    }

    // 선택한 게시물
    @GetMapping("/{postNumber}")
    public Response<?> getPost(){
        return success();
    }

    // 게시글 제목으로 검색한 게시물 리스트
    @GetMapping()
    public Response<?> getSearchPosts(){
        return success();
    }
}
