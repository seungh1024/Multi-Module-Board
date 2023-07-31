package com.seungh1024;

import com.seungh1024.entity.comment.Comment;
import com.seungh1024.entity.member.Member;
import com.seungh1024.entity.post.Post;
import com.seungh1024.repository.member.MemberRepository;
import com.seungh1024.repository.post.PostRepository;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Commit
class PostRepositoryTest {
    @Autowired
    EntityManager em;

    @Autowired
    PostRepository postRepository;

    @Autowired
    MemberRepository memberRepository;

//    @BeforeEach
//    @Commit
//    public void before(){
//        em.createQuery("delete from Post");
//
//        Member member = em.find(Member.class,1);
//        for(int i = 1; i <= 10000; i++){
//            Post post = new Post("testName "+i , "testContent "+i);
//            em.persist(post);
//        }
//        Post post = em.find(Post.class,1);
//        for(int j = 1; j <=1000; j++){
//            Comment comment = new Comment("JMeter Content Test "+j);
//            comment.addFk(member,post);
//            em.persist(comment);
//        }
//    }

    @Test
    public void checkPost(){
        String jpqlString = "select count(p) from Post p";
        Long count = em.createQuery(jpqlString, Long.class).getSingleResult();
        Assertions.assertThat(count).isEqualTo(10000);
    }

}