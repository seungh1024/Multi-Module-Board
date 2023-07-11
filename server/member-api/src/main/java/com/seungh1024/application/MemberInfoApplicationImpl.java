package com.seungh1024.application;


import com.seungh1024.dto.MemberInfoDto;
import com.seungh1024.dto.MemberReqDto;
import com.seungh1024.encrypt.RandomSalt;
import com.seungh1024.encrypt.SeunghPasswordEncoder;
import com.seungh1024.entity.member.Member;
import com.seungh1024.repository.member.MemberRepository;
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
    public void updateMemberInfo(MemberReqDto.InfoDto memberDto, Long memberId){
        Member member = memberRepository.findById(memberId).get();
        String salt = randomSalt.getSalt();
        String encodedPassword = seunghPasswordEncoder.encryptPassword(memberDto.getMemberPassword(),salt);
        member.updatePassword( encodedPassword,salt);
        member.updateName(memberDto.getMemberName());
        member.updateAge(memberDto.getMemberAge());
        memberRepository.save(member);
    }
}
