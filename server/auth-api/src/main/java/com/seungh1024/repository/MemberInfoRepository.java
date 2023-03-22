package com.seungh1024.repository;

import com.seungh1024.member.MemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * MemberInfo JPA
 *
 * @Author 강승훈
 * @Since 2023.03.20
 *
 * */
public interface MemberInfoRepository extends JpaRepository<MemberInfo,Integer> {

}
