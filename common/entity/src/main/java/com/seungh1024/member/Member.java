package com.seungh1024.member;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
public class Member {
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
