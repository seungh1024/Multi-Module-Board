package com.seungh1024.repository.member;

import com.seungh1024.entity.member.Member;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/*
 * Member JPA
 *
 * @Author 강승훈
 * @Since 2023.03.20
 *
 * */
public interface MemberRepository extends JpaRepository<Member,Long> {
    @Query("select m from Member m where m.memberEmail = :memberEmail")
    Member searchMember(@Param("memberEmail") String memberEmail); //사용자 검색
}
