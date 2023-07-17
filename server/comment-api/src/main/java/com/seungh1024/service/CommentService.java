package com.seungh1024.service;

import com.seungh1024.dto.CommentCreateBodyDto;
import com.seungh1024.dto.CommentUpdateBodyDto;
import com.seungh1024.repository.comment.condition.CommentCondition;
import com.seungh1024.repository.comment.dto.CommentQueryDto;
import com.seungh1024.repository.comment.dto.MyCommentQueryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/*
 * Comment Service
 *
 * @Author 강승훈
 * @Since 2023.07.14
 *
 * */
@Service
public interface CommentService {
    Page<CommentQueryDto> getCommentList(CommentCondition condition, Pageable pageable);

    void modifyComment(Long memberId, CommentUpdateBodyDto commentDto);

    Page<MyCommentQueryDto> getMyCommentList(Long memberId, Pageable pageable);
}
