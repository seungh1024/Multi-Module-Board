package com.seungh1024.member;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

/*
* 사용자 상세 정보 엔티티 클래스
*
 * @Author 강승훈
 * @Since 2023.03.20
*
* */

@Entity
@NoArgsConstructor
public class MemberInfo {
    @Id
    @Column(name = "info_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int infoId;

    @Column(name = "member_age")
    private int memberAge;

    @Builder
    public MemberInfo(int memberAge){
        this.memberAge = memberAge;
    }
}
