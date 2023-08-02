package com.seungh1024.dto.response;

/*
 * 게시글 정보 DTO(응답용)
 *
 * @Author 강승훈
 * @Since 2023.07.31
 *
 * */

import com.fasterxml.jackson.annotation.JsonFormat;
import com.seungh1024.entity.post.Post;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

import static com.seungh1024.time.FormatUtils.BASIC_TIME_PATTERN;

@ToString
@Getter
public class PostResDto {
    private final Long postId;
    private final String postName;
    private final Long memberId;
    private final String memberName;
    private final Integer postViews;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = BASIC_TIME_PATTERN)
    private final LocalDateTime createdAt;

    public PostResDto(Post post){
        this.postId = post.getPostId();
        this.postName = post.getPostName();
        this.memberId = post.getMember().getMemberId();
        this.memberName = post.getMember().getMemberName();
        this.postViews = post.getPostViews();
        this.createdAt = post.getCreatedAt();
    }
}
