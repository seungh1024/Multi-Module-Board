package com.seungh1024.entity.comment;

/*
 * 게시글의 댓글 엔티티
 *
 * @Author 강승훈
 * @Since 2023.07.14
 *
 * */

import com.seungh1024.entity.base.BaseEntity;
import com.seungh1024.entity.member.Member;
import com.seungh1024.entity.post.Post;
import com.seungh1024.repository.comment.dto.CommentUpdateDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
@Getter
public class Comment extends BaseEntity {
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(name = "comment_content")
    private String commentContent;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment(){};

    public Comment(Long commentId, String commentContent){
        this.commentId = commentId;
        this.commentContent = commentContent;
    }

    public Comment(String commentContent){
        this.commentContent = commentContent;
    }


    public void addFk(Member member, Post post) {
        this.member = member;
        this.post = post;
    }

    public void updateComment(CommentUpdateDto commentUpdateDto){
        this.commentContent = commentUpdateDto.getCommentContent();
        this.updatedAt = LocalDateTime.now();
    }

    public boolean isOwner(Long memberId){
        if(this.member.getMemberId() == memberId){
            return true;
        }else{
            return false;
        }
    }
}