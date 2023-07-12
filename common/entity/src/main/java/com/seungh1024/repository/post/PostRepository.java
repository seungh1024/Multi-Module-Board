package com.seungh1024.repository.post;

import com.seungh1024.entity.post.Post;
import com.seungh1024.repository.post.querydsl.PostRepositoryCustom;
import com.seungh1024.repository.post.querydsl.PostRepositoryImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * PostRepository -> 게시글 JPA Repository
 *
 * @Author 강승훈
 * @Since 2023.06.27
 *
 * */

public interface PostRepository extends JpaRepository<Post,Long> , PostRepositoryCustom {
}
