package com.seungh1024.repository.post.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.seungh1024.entity.post.Post;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.cglib.core.Local;

import java.math.BigInteger;
import java.sql.ResultSet;
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
//@Setter
@ToString
public class PostDetailQueryDto {
    private final Long postId;
    private final String postName;
    private final String postContent;
    private final LocalDateTime postCreatedAt;
    private final Integer postViews;
    private final Long postMemberId;
    private final String postMemberName;
    private final Long commentId;
    private final String commentContent;
    private final LocalDateTime commentCreatedAt;
    private final Long commentMemberId;
    private final String commentMemberName;

    //querydsl native query 생성자
    public PostDetailQueryDto(Object[] o) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
        this.postId = Long.parseLong(o[0].toString());
        this.postName = o[1].toString();
        this.postContent = o[2].toString();
        String s = o[3].toString();
        if(s.length() <26){
            s=s+"0";
        }
        this.postCreatedAt = LocalDateTime.parse(s,formatter);
        this.postViews =  Integer.parseInt(o[4].toString());
        this.postMemberId =  Long.parseLong(o[5].toString());
        this.postMemberName = o[6].toString();
        this.commentId = o[7] == null ? null : Long.parseLong(o[7].toString());
        this.commentContent = o[8] == null ? null :(String) o[8];
        s = o[9].toString();
        if(s.length() <26){
            s=s+"0";
        }
        this.commentCreatedAt = o[9] == null ? null : LocalDateTime.parse(s,formatter);
        this.commentMemberId = o[10] == null? null: Long.parseLong(o[10].toString());
        this.commentMemberName = o[10] == null? null: (String) o[11];
    }

    //querydsl 생성자
    public PostDetailQueryDto(Long postId, String postName, String postContent, LocalDateTime postCreatedAt, Integer postViews, Long postMemberId, String postMemberName, Long commentId, String commentContent, LocalDateTime commentCreatedAt, Long commentMemberId, String commentMemberName) {
        this.postId = postId;
        this.postName = postName;
        this.postContent = postContent;
        this.postCreatedAt = postCreatedAt;
        this.postViews = postViews;
        this.postMemberId = postMemberId;
        this.postMemberName = postMemberName;
        this.commentId = commentId;
        this.commentContent = commentContent;
        this.commentCreatedAt = commentCreatedAt;
        this.commentMemberId = commentMemberId;
        this.commentMemberName = commentMemberName;
    }

    //jdbc 생성자
    public PostDetailQueryDto(Long postId, String postName, String postContent, Timestamp postCreatedAt, Integer postViews, Long postMemberId, String postMemberName, Long commentId, String commentContent, Timestamp commentCreatedAt, Long commentMemberId, String commentMemberName) {
        this.postId = postId != null? postId:null;
        this.postName = postName != null? postName:null;;
        this.postContent = postContent != null? postContent:null;;
        this.postCreatedAt = postCreatedAt != null? postCreatedAt.toLocalDateTime():null;;
        this.postViews = postViews != null? postViews:null;;
        this.postMemberId = postMemberId != null? postMemberId:null;;
        this.postMemberName = postMemberName != null? postMemberName:null;;
        this.commentId = commentId != null? commentId:null;;
        this.commentContent = commentContent != null? commentContent:null;;
        this.commentCreatedAt = commentCreatedAt != null? commentCreatedAt.toLocalDateTime():null;;
        this.commentMemberId = commentMemberId != null? commentMemberId:null;;
        this.commentMemberName = commentMemberName != null? commentMemberName:null;;
    }
}
