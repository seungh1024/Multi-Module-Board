package com.seungh1024.service;

import com.seungh1024.dto.MemberDto;
import com.seungh1024.entity.Member;
import com.seungh1024.exception.custom.DuplicateMemberException;
import com.seungh1024.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    //사용자 등록
    public Member createMember(MemberDto.MemberJoinRequest memberDto){
        int memberCheck = memberRepository.countMemberByMemberEmail(memberDto.getMemberEmail());
        if(memberCheck > 0){ // 1이상이면 중복되는 누군가 있는 것

            throw new DuplicateMemberException(memberDto.getMemberEmail());
        }

        Member member = Member.builder()
                .memberEmail(memberDto.getMemberEmail())
                .memberPassword(memberDto.getMemberPassword())
                .memberName(memberDto.getMemberName())
                .build();
        member = memberRepository.save(member);
        return member;
    }

    //전체 사용자 조회
    public List<Member> allMemberList(){
        return memberRepository.findAll();
    }

    //사용자 이메일로 검색
   public Member findMemberByEmail(String email){
       Member member = memberRepository.findMemberByMemberEmail(email);
       System.out.println("member: "+member);
       if(member == null){
           throw new EntityNotFoundException();
       }
       return  member;
    }

    //TODO 업데이트,삭제 로그인 연동되면 그걸로 pk받아와서 수정 및 삭제로 변경
    //사용자 비밀번호 업데이트
    public void updateMemberPassword(int pk,String password){
        Member member = memberRepository.findMemberByMemberId(pk);
        if(member == null){
            throw new EntityNotFoundException();
        }
        member.updatePassword(password);
        member = memberRepository.save(member);
    }

    //사용자 삭제
    public void deleteMember(int pk){
        Member member = memberRepository.findMemberByMemberId(pk);
        if(member == null){
            throw new EntityNotFoundException();
        }
        memberRepository.delete(member);
    }
}
