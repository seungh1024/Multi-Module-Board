package com.seungh1024.entity.post;
/*
 * 게시글 Entity
 *
 * @Author 강승훈
 * @Since 2023.06.27
 *
 * */

import com.seungh1024.entity.base.BaseEntity;
import com.seungh1024.entity.member.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/*
 * 게시글 엔티티
 *
 * @Author 강승훈
 * @Since 2023.07.11
 *
 * */
@Entity
@Table(name = "post")
@Getter
public class Post extends BaseEntity {

    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(name = "post_name")
    private String postName; // 게시글 제목

    @Lob // DB에서 varchar 사이즈 넘어서는 큰 컨텐츠 넣고 싶을 때 사용
    @Column(name = "post_content")
    private String postContent;

    @Column(name = "post_views")
    private Integer postViews = 0;

    // 여러개의 게시글은 각각의 사용자가 있다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Post(){};

//    @Builder
    private Post(String postName, String postContent){
        this.postName = postName;
        this.postContent = postContent;
    }

    public static Post createPost(String postName, String postContent){
        return new Post(postName, postContent);
    }

    public void updateMember(Member member){

        this.member = member;
        member.getPosts().add(this);
    }

}
