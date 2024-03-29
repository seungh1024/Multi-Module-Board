package com.seungh1024.repository.comment.querydsl;

import com.seungh1024.entity.comment.Comment;
import com.seungh1024.repository.comment.condition.CommentCondition;
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
    Page<Comment> getCommentList(CommentCondition condition, Pageable pageable);
    Page<Comment> getMyCommentList(Long memberId, Pageable pageable);
}
