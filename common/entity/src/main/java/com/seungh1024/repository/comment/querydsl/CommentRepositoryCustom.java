package com.seungh1024.repository.comment.querydsl;

import com.seungh1024.repository.comment.condition.CommentCondition;
import com.seungh1024.repository.comment.dto.CommentQueryDto;
import com.seungh1024.repository.comment.dto.MyCommentQueryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/*
 * Comment Querydsl
 *
 * @Author 강승훈
 * @Since 2023.07.14
 *
 * */
public interface CommentRepositoryCustom {
    Page<CommentQueryDto> getCommentList(CommentCondition condition, Pageable pageable);
    Page<MyCommentQueryDto> getMyCommentList(Long memberId, Pageable pageable);
}
