package com.seungh1024.dto;

import com.seungh1024.member.Member;

/*
 * 회원 응답 정보 DTo
 *
 *  @Author 강승훈
 *  @Since 2023.06.06
 *
 * */
public class MemberInfoDto {

    public final String memberEmail;
    public final String memberName;
    public final int memberAge;


    private MemberInfoDto(String memberEmail, String memberName, int memberAge){
        this.memberEmail = memberEmail;
        this.memberName = memberName;
        this.memberAge = memberAge;
    }

    public static MemberInfoDto getAllMemberInfo(Member member){
        return new MemberInfoDto(member.getMemberEmail(),member.getMemberName(), member.getMemberInfo().getMemberAge());
    }
}
