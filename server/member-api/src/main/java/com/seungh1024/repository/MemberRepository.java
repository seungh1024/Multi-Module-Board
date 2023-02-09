package com.seungh1024.repository;

import com.seungh1024.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    //사용자 이메일로 조회 -> 하나만 매칭
    Member findMemberByMemberEmail(String memberEmail);

    //사용자 pk로 조회
    Member findMemberByMemberId(int memberId);
}
