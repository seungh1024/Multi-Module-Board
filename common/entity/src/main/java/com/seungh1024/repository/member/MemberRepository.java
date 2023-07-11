package com.seungh1024.repository.member;

import com.seungh1024.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * Member JPA
 *
 * @Author 강승훈
 * @Since 2023.03.20
 *
 * */
public interface MemberRepository extends JpaRepository<Member,Long> {
    Member findMemberByMemberEmail(String memberEmail); //사용자 검색
}
