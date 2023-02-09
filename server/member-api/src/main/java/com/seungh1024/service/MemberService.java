package com.seungh1024.service;

import com.seungh1024.entity.Member;
import com.seungh1024.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    //사용자 등록
    public boolean createMember(Member member){
        try{
            memberRepository.save(member);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    //전체 사용자 조회
    public List<Member> allMemberList(){
        return memberRepository.findAll();
    }

    //사용자 이메일로 검색
    public Member findMemberByEmail(String email){
        return memberRepository.findMemberByMemberEmail(email);
    }

    //사용자 비밀번호 업데이트
    public boolean updateMemberPassword(int pk,String password){
        try{
            Member member = memberRepository.findMemberByMemberId(pk);
            member.updatePassword(password);
            memberRepository.save(member);
            return true;
        }catch(Exception e){
//            e.printStackTrace();
            return false;
        }
    }

    //사용자 삭제
    public boolean deleteMember(int pk){
        try{
            Member member = memberRepository.findMemberByMemberId(pk);
            memberRepository.delete(member);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
