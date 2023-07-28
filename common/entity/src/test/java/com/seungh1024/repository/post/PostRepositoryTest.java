package com.seungh1024.repository.post;

import com.seungh1024.entity.post.Post;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostRepositoryTest {
    @Autowired
    EntityManager em;


    @Test
    public void checkPost(){
        String jpqlString = "select count(p) from Post p";
        int count = em.createQuery(jpqlString, Integer.class).getSingleResult();
        Assertions.assertThat(count).isEqualTo(10000);
    }
}