package com.seungh1024.service;

import com.seungh1024.dto.MemberInfoDto;
import com.seungh1024.dto.MemberReqDto;

/*
 * 회원 상세 정보 인터페이스
 *
 *  @Author 강승훈
 *  @Since 2023.07.28
 *
 * */
public interface MemberInfoService {
    MemberInfoDto getMemberInfo(Long memberId);

    void updateMemberInfo(MemberReqDto.InfoDto memberDto, Long memberId);
}
