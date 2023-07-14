package com.seungh1024.entity.post;
/*
 * 게시글 Entity
 *
 * @Author 강승훈
 * @Since 2023.06.27
 *
 * */

import com.seungh1024.entity.base.BaseEntity;
import com.seungh1024.entity.comment.Comment;
import com.seungh1024.entity.member.Member;
import com.seungh1024.repository.post.condition.PostDetailCondition;
import com.seungh1024.repository.post.dto.PostDetailQueryDto;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // 여러개의 게시글은 각각의 사용자가 있다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    public Post(){};

    public Post(Long postId, String postName, String postContent, Integer postViews,LocalDateTime createdAt, Member member){
        this.postId = postId;
        this.postName = postName;
        this.postContent = postContent;
        this.postViews = postViews;
        this.member = member;
    }


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
//        member.getPosts().add(this);
    }

    public void updateViews(){
        this.postViews += 1;
    }
    public void updatePost(PostDetailCondition condition){
        if(condition.getPostName() != null){
            this.postName = condition.getPostName();
        }
        if(condition.getPostContent() != null){
            this.postContent = condition.getPostContent();
        }
        this.updatedAt = LocalDateTime.now();
    }

    public PostDetailQueryDto entityToDto(){
        return new PostDetailQueryDto(
                this.postId,
                this.postName,
                this.member.getMemberName(),
                this.postViews,
                this.getCreatedAt(),
                this.postContent
        );
    }

    public boolean isOwner(Long memberId){
        if(this.getMember().getMemberId() != memberId){
            return false;
        }
        return true;
    }

}
