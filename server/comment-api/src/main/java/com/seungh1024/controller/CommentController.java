package com.seungh1024.controller;

import com.seungh1024.Response;
import com.seungh1024.application.CommentApplication;
import com.seungh1024.dto.CommentCreateBodyDto;
import com.seungh1024.dto.CommentUpdateBodyDto;
import com.seungh1024.repository.comment.condition.CommentCondition;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.seungh1024.Response.success;

/*
 * Comment Controller
 *
 * @Author 강승훈
 * @Since 2023.07.14
 *
 * */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {
    private final CommentApplication commentApplication;

    @PostMapping("/create")
    public Response<?> createComment(@AuthenticationPrincipal Long memberId, @RequestBody CommentCreateBodyDto commentDto){
        commentApplication.createComment(memberId, commentDto);
        return success();
    }
    @GetMapping("/list")
    public Response<?> getCommentList(CommentCondition condition, Pageable pageable){
        return success(commentApplication.getCommentList(condition, pageable));
    }

    @PatchMapping("/modify")
    public Response<?> modifyComment(@AuthenticationPrincipal Long memberId, @RequestBody @Valid CommentUpdateBodyDto commentDto){
        commentApplication.modifyComment(memberId, commentDto);
        return success();
    }

    @GetMapping("/my/list")
    public Response<?> getMyCommentList(@AuthenticationPrincipal Long memberId, Pageable pageable){
        return success(commentApplication.getMyCommentList(memberId, pageable));
    }
}
