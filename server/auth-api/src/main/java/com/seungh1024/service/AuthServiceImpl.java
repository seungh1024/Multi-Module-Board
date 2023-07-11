package com.seungh1024.service;

import com.seungh1024.dto.MemberReqDto;
import com.seungh1024.encrypt.RandomSalt;
import com.seungh1024.encrypt.SeunghPasswordEncoder;
import com.seungh1024.exception.custom.DuplicateMemberException;
import com.seungh1024.entity.member.Member;
import com.seungh1024.entity.member.MemberInfo;
import com.seungh1024.repository.member.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/*
* 트랜잭션 처리가 필요한 서비스 클래스
*
* @Author 강승훈
* @Since 2023.03.20
* */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final MemberRepository memberRepository;
    private final SeunghPasswordEncoder seunghPasswordEncoder;
    private final RandomSalt randomSalt;

    @Transactional
    public void signup(MemberReqDto.JoinForm memberDto){
        String memberEmail = memberDto.getMemberEmail();
        String memberPassword = memberDto.getMemberPassword();
        String memberName = memberDto.getMemberName();
        int memberAge = memberDto.getMemberAge();

        Member member = memberRepository.findMemberByMemberEmail(memberEmail);
        if(member != null){ //이미 존재하면 예외처리
            throw new DuplicateMemberException(memberEmail);
        }

        String salt = randomSalt.getSalt();
        String encodedPassword = seunghPasswordEncoder.encryptPassword(memberPassword,salt);


        MemberInfo memberInfo = MemberInfo.createMemberInfo(memberAge);
        Member saveMember = Member.createMember(memberEmail,encodedPassword,memberName,salt, memberInfo);
        memberRepository.save(saveMember);


    }
}
