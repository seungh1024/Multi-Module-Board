package com.seungh1024.service;

import com.seungh1024.dto.CommentCreateBodyDto;
import com.seungh1024.dto.CommentResDto;
import com.seungh1024.dto.CommentUpdateBodyDto;
import com.seungh1024.repository.comment.condition.CommentCondition;
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
    Page<CommentResDto> getCommentList(CommentCondition condition, Pageable pageable);
    void createComment(Long memberId, CommentCreateBodyDto commentDto);

    void modifyComment(Long memberId, CommentUpdateBodyDto commentDto);

    Page<CommentResDto> getMyCommentList(Long memberId, Pageable pageable);
}
