package com.seungh1024.service;

import com.seungh1024.entity.comment.Comment;
import com.seungh1024.entity.member.Member;
import com.seungh1024.entity.post.Post;
import com.seungh1024.repository.comment.CommentRepository;
import com.seungh1024.repository.post.PostRepository;
import com.seungh1024.repository.post.condition.PostDetailCondition;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceImplTest {
    @Autowired
    PostRepository postRepository;
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    EntityManager em;

    @BeforeEach
    @Commit
    public void before(){
        em.createQuery("delete from Post");

        Member member = em.find(Member.class,1);
        for(int i = 1; i <= 10; i++){
            Post post = new Post("testName "+i , "testContent "+i);
            postRepository.save(post);
//            em.persist(post);
        }
        Post post = em.find(Post.class,1);
        for(int j = 1; j <=10; j++){
            Comment comment = new Comment("JMeter Content Test "+j);
            comment.addFk(member,post);
            commentRepository.save(comment);
//            em.persist(comment);
        }
    }

    @Test
    public void jdbcRepositoryTest(){
        PostDetailCondition condition = new PostDetailCondition(1L,null,null);
        postRepository.getPostDetails(condition);
    }
}