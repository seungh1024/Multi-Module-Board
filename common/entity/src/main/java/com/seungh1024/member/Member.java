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
@NoArgsConstructor
public class Member {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberId;

    @Column(name = "member_email", unique = true)
    private String memberEmail;

    @Column(name = "member_password")
    private String memberPassword;

    @Column(name = "member_name")
    private String memberName;

    //상세 정보와 1:1 매핑
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "info_id")
    private MemberInfo memberInfo;


    @Builder
    public Member(int memberId, String memberEmail, String memberPassword, String memberName, MemberInfo memberInfo){
        this.memberId = memberId;
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberName = memberName;
        this.memberInfo = memberInfo;
    }

    public void updatePassword(String memberPassword){
        this.memberPassword = memberPassword;
    }
}
