package com.seungh1024.application;

import com.seungh1024.dto.CommentCreateBodyDto;
import com.seungh1024.dto.CommentResDto;
import com.seungh1024.dto.CommentUpdateBodyDto;
import com.seungh1024.repository.comment.condition.CommentCondition;
import com.seungh1024.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/*
 * Comment Application 구현 클래스
 *
 * @Author 강승훈
 * @Since 2023.07.14
 *
 * */
@Service
@RequiredArgsConstructor
public class CommentApplicationImpl implements CommentApplication {
    private final CommentService commentService;

    @Override
    public Page<CommentResDto> getCommentList(CommentCondition condition, Pageable pageable) {
        return commentService.getCommentList(condition, pageable);
    }

    @Override
    public void createComment(Long memberId, CommentCreateBodyDto commentDto) {
        commentService.createComment(memberId,commentDto);
    }

    @Override
    public void modifyComment(Long memberId, CommentUpdateBodyDto commentDto) {
        commentService.modifyComment(memberId, commentDto);
    }

    @Override
    public Page<CommentResDto> getMyCommentList(Long memberId, Pageable pageable) {
        return commentService.getMyCommentList(memberId,pageable);
    }
}
