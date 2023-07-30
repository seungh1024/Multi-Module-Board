package com.seungh1024.repository.post.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.seungh1024.entity.post.Post;
import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * 게시글 상세 정보 DTO(게시글 클릭 후 보이는 정보)
 *
 * @Author 강승훈
 * @Since 2023.07.12
 *
 * */
@Getter
public class PostDetailQueryDto {
    private final Long post_id;
    private final String post_name;
    private final String post_content;
    private final LocalDateTime post_createdAt;
    private final Integer post_views;
    private final Long member_id;
    private final String member_name;
    private final Long comment_id;
    private final String comment_content;
    private final LocalDateTime comment_createdAt;


    public PostDetailQueryDto(Long post_id, String post_name, Long member_id, String member_name, Integer post_views, LocalDateTime post_createdAt, String post_content, Long comment_id, String comment_content, LocalDateTime comment_createdAt) {
        this.post_id = post_id;
        this.post_name = post_name;
        this.member_id = member_id;
        this.member_name = member_name;
        this.post_views = post_views;
        this.post_createdAt = post_createdAt;
        this.post_content = post_content;
        this.comment_id = comment_id;
        this.comment_content = comment_content;
        this.comment_createdAt = comment_createdAt;
    }

    public PostDetailQueryDto(Object[] o) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
        this.post_id = Long.parseLong(o[0].toString());
        this.post_name = o[1].toString();
        this.post_content = o[2].toString();
        this.post_createdAt = LocalDateTime.parse(o[3].toString(),formatter);
        this.post_views =  Integer.parseInt(o[4].toString());
        this.member_id =  Long.parseLong(o[5].toString());
        this.member_name = o[6].toString();
        this.comment_id = Long.parseLong(o[7].toString());
        this.comment_content = (String) o[8];
        this.comment_createdAt = LocalDateTime.parse(o[9].toString(),formatter);
    }
}
