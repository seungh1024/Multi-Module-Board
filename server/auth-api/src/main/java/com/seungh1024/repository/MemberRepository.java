package com.seungh1024.repository;

import com.seungh1024.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * Member JPA
 *
 * @Author 강승훈
 * @Since 2023.03.20
 *
 * */
public interface MemberRepository extends JpaRepository<Member,Integer> {
    Member findMemberByMemberEmail(String memberEmail); //사용자 검색
}
