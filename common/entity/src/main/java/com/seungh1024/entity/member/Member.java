package com.seungh1024.entity.member;

import com.seungh1024.entity.base.BaseEntity;
import com.seungh1024.entity.post.Post;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/*
 * 사용자 정보 엔티티 클래스
 *
 * @Author 강승훈
 * @Since 2023.03.20
 *
 * */

@Entity
@Table(name = "member")
@Getter
public class Member extends BaseEntity {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(name = "member_email", unique = true)
    private String memberEmail;

    @Column(name = "member_password")
    private String memberPassword;

    @Column(name = "member_name")
    private String memberName;

    @Column(name = "member_salt")
    private String memberSalt;

    //상세 정보와 1:1 매핑
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "info_id")
    private MemberInfo memberInfo;

    // 사용자는 여러 개의 게시글을 작성할 수 있다.
    @OneToMany(mappedBy = "postId")
    private List<Post> posts = new ArrayList<>();


    private Member(String memberEmail, String memberPassword, String memberName,String memberSalt,MemberInfo memberInfo){
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberName = memberName;
        this.memberSalt = memberSalt;
        this.memberInfo = memberInfo;
    }

    public Member() {} //spring jpa 사용하려면 기본 생성자가 public으로 있어야 함. 나는 다른 생성자를 private으로 해서 기본 생성자를 이렇게 만들어줘야 함.

    public static Member createMember(String memberEmail, String memberPassword, String memberName, String memberSalt, MemberInfo memberInfo){
        return new Member(memberEmail,memberPassword,memberName,memberSalt, memberInfo);
    }

    //TODO 회원 상세 정보 업데이트 메소드


    public void updatePassword(String memberPassword, String salt){
        if(memberPassword != null) {
            this.memberPassword = memberPassword;
            this.memberSalt = salt;
        }
    }
    public void updateName(String memberName){
        if(memberName != null) this.memberName = memberName;
    }
    public void updateAge(Integer memberAge){
        if(memberAge != null) this.memberInfo.updateAge(memberAge);
    }

}
