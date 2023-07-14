package com.seungh1024.repository.comment;

import com.seungh1024.entity.comment.Comment;
import com.seungh1024.repository.comment.querydsl.CommentRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * Comment Repository
 *
 * @Author 강승훈
 * @Since 2023.07.14
 *
 * */
public interface CommentRepository extends JpaRepository<Comment, Long> , CommentRepositoryCustom {
}
