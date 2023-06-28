package com.seungh1024.repository;

import com.seungh1024.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;


/*
 * MemberRepository : 사용자 관련 레포지토리
 *
 * @Author 강승훈
 * @Since 2023.06.27
 *
 * */
public interface MemberRepository extends JpaRepository<Member,Long> {
    Member findMemberByMemberEmail(String memberEmail);
}
