package com.seungh1024.repository;

import com.seungh1024.member.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * PostRepository -> 게시글 JPA Repository
 *
 * @Author 강승훈
 * @Since 2023.06.27
 *
 * */
public interface PostRepository extends JpaRepository<Post,Long> {
}
