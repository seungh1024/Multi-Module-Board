package com.seungh1024.controller;

import com.seungh1024.entity.comment.Comment;
import com.seungh1024.entity.post.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class PostDetailV1 {
    private Long postId;
    private String postName;
    private Long memberId;
    private String memberName;
    private Integer postViews;
    private LocalDateTime createdAt;
    private String postContent;
    private List<CommentDetailV1> commentList;
    public PostDetailV1(Post post) {
        this.postId = post.getPostId();
        this.postName = post.getPostName();
        this.memberId = post.getMember().getMemberId();
        this.memberName = post.getMember().getMemberName();
        this.postViews =post.getPostViews();
        this.createdAt = post.getCreatedAt();
        this.postContent = post.getPostContent();
        commentList = new ArrayList<>();
        for(Comment c : post.getComments()){
            commentList.add(new CommentDetailV1(c));
        }

    }
}
