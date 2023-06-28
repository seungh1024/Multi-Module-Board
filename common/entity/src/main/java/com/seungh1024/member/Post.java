package com.seungh1024.member;
/*
 * 게시글 Entity
 *
 * @Author 강승훈
 * @Since 2023.06.27
 *
 * */

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "post")
@Getter
@EntityListeners(value = AuditingEntityListener.class)
public class Post {

    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(name = "post_name")
    private String postName; // 게시글 제목

    @Lob // DB에서 varchar 사이즈 넘어서는 큰 컨텐츠 넣고 싶을 때 사용
    @Column(name = "post_content")
    private String postContent;

    @CreatedDate // 엔티티 생성 시 자동으로 시간이 들어간다
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false , updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @LastModifiedDate //엔티티 수정 시 자동으로 시간이 들어간다.
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false , updatable = true, columnDefinition = "TIMESTAMP ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    // 여러개의 게시글은 각각의 사용자가 있다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_email")
    private Member member;

    public Post(){};

    @Builder
    private Post(String postName, String postContent){
        this.postName = postName;
        this.postContent = postContent;
    }

    public static Post createPost(String postName, String postContent){
        return new Post(postName, postContent);
    }

    public void updateMember(Member member){
        this.member = member;
    }

}
