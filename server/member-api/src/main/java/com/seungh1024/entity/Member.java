package com.seungh1024.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "member")
@Getter

// toString을 Entity에 사용하면 지연 로딩 시 지연하지 않고 모두 찍어버리는 문제 발생
// 또한 다른 엔티티와 서로 참조하는 관계로 설정되어 있다면 테스트 한다고 출력 시에 서로 무한으로 참조하며 출력되어 서버가 죽어버림
//@ToString(exclude = "memberPassword")
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

    @Builder
    public Member(int memberId, String memberEmail, String memberPassword, String memberName){
        this.memberId = memberId;
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberName = memberName;
    }

    public void updatePassword(String memberPassword){
        this.memberPassword = memberPassword;
    }
}
