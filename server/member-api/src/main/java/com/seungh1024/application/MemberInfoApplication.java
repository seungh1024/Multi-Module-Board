package com.seungh1024.application;

import com.seungh1024.dto.MemberInfoDto;
import com.seungh1024.dto.MemberReqDto;

/*
 * 트랜잭션 필요 없는 회원 정보 인터페이스
 *
 *  @Author 강승훈
 *  @Since 2023.06.06
 *
 * */
public interface MemberInfoApplication {
    MemberInfoDto getMemberInfo(String memberEmail);

    void updateMemberInfo(MemberReqDto.InfoDto memberReqDto, String memberEmail);
}
