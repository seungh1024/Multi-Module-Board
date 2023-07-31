package com.seungh1024.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.seungh1024.repository.post.dto.PostDetailQueryDto;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.seungh1024.time.FormatUtils.BASIC_TIME_PATTERN;

/*
 * 게시글 상세 정보 DTO(응답용)
 *
 * @Author 강승훈
 * @Since 2023.07.30
 *
 * */

@Getter
public class PostDetailResDto {
    private final Long postId;
    private final String postName;
    private final Long memberId;
    private final String memberName;
    private final Integer postViews;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = BASIC_TIME_PATTERN)
    private final LocalDateTime createdAt;
    private final String postContent;
    private final List<CommentDetailResDto> commentList;


    public PostDetailResDto(List<PostDetailQueryDto> selectPost) {
        PostDetailQueryDto postDetail = selectPost.get(0);
        this.postId = postDetail.getPostId();
        this.postName = postDetail.getPostName();
        this.memberId = postDetail.getPostMemberId();
        this.memberName = postDetail.getPostMemberName();
        this.postViews = postDetail.getPostViews();
        this.createdAt = postDetail.getPostCreatedAt();
        this.postContent = postDetail.getPostContent();
        if(postDetail.getCommentId() != null){
            this.commentList = selectPost.stream()
                    .map(comment -> new CommentDetailResDto(comment.getCommentId(),comment.getCommentContent(), comment.getCommentCreatedAt(),comment.getCommentMemberId(), comment.getCommentMemberName()))
                    .toList();
        }else{
            this.commentList = new ArrayList<>();
        }
    }


}
