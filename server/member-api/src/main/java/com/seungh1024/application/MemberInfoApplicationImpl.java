package com.seungh1024.application;


import com.seungh1024.dto.MemberInfoDto;
import com.seungh1024.dto.MemberReqDto;
import com.seungh1024.service.MemberInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/*
 * 회원 정보 컨트롤러
 *
 *  @Author 강승훈
 *  @Since 2023.06.06
 *
 * */
@Service
@RequiredArgsConstructor
public class MemberInfoApplicationImpl implements MemberInfoApplication{
    private final MemberInfoService memberInfoService;
    @Override
    public MemberInfoDto getMemberInfo(Long memberId) {
        return memberInfoService.getMemberInfo(memberId);
    }

    @Override
    public void updateMemberInfo(MemberReqDto.InfoDto memberDto, Long memberId){
        memberInfoService.updateMemberInfo(memberDto,memberId);
    }
}
