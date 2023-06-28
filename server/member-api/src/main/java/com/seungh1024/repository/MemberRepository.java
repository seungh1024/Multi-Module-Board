package com.seungh1024.repository;

import com.seungh1024.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Member findMemberByMemberEmail(String memberEmail);
}
