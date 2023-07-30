package com.seungh1024.repository.post.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.seungh1024.entity.comment.Comment;
import com.seungh1024.repository.base.BaseTime;
import com.seungh1024.repository.comment.dto.CommentQueryDto;
import lombok.Getter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*
 * 게시글 목록 DTO
 *
 * @Author 강승훈
 * @Since 2023.07.11
 *
 * */
@Getter
@ToString
public class PostMemberQueryDto extends BaseTime {
    private final Long postId;
    private final String postName;
    private final String memberName;
    private final Integer postViews;

    public PostMemberQueryDto(Long postId, String postName, String memberName, Integer postViews, LocalDateTime createdAt){
        super(createdAt);
        this.postId = postId;
        this.postName = postName;
        this.memberName = memberName;
        this.postViews = postViews;
    }
}

