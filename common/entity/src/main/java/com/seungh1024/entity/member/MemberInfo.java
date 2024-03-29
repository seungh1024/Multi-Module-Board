package com.seungh1024.entity.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
* 사용자 상세 정보 엔티티 클래스
*
 * @Author 강승훈
 * @Since 2023.03.20
*
* */

@Entity
@Table(name = "member_info")
@NoArgsConstructor
@Getter
public class MemberInfo {
    @Id
    @Column(name = "info_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberInfoId;

    @Column(name = "member_age")
    private Integer memberAge;

    @OneToOne(mappedBy = "memberInfo")
    private Member member;


    public MemberInfo(int memberAge){
        this.memberAge = memberAge;
    }

    public void updateAge(int memberAge){this.memberAge = memberAge; }
}
