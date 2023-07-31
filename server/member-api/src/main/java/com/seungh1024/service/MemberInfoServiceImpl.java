package com.seungh1024.service;

import com.seungh1024.application.MemberInfoApplication;
import com.seungh1024.dto.MemberInfoDto;
import com.seungh1024.dto.MemberReqDto;
import com.seungh1024.encrypt.RandomSalt;
import com.seungh1024.encrypt.SeunghPasswordEncoder;
import com.seungh1024.entity.member.Member;
import com.seungh1024.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 * 회원 상세 정보 구현 클래스
 *
 *  @Author 강승훈
 *  @Since 2023.07.28
 *
 * */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberInfoServiceImpl implements MemberInfoApplication {
    private final MemberRepository memberRepository;
    private final RandomSalt randomSalt;
    private final SeunghPasswordEncoder seunghPasswordEncoder;
    @Override
    public MemberInfoDto getMemberInfo(Long memberId) {
        Member member = memberRepository.findById(memberId).get();
        MemberInfoDto memberInfoDto = MemberInfoDto.getAllMemberInfo(member);
        return memberInfoDto;
    }

    @Override
    public void updateMemberInfo(MemberReqDto.InfoDto memberDto, Long memberId) {
        Member member = memberRepository.findById(memberId).get();
        String salt = randomSalt.getSalt();
        String encodedPassword = seunghPasswordEncoder.encryptPassword(memberDto.getMemberPassword(),salt);
        member.updateInfo(memberDto.getMemberName(),memberDto.getMemberAge(),encodedPassword,salt);
        memberRepository.save(member);
    }
}
