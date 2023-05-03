package com.seungh1024.dto;

import lombok.Builder;
import lombok.Getter;
/*
* Member Response DTO : 응답용 DTO
*
* @Author 강승훈
* @Since 2023.05.03
*
 */

public class MemberResDto {
    @Getter
    @Builder
    public static class AllArgs{
        public int memberId;
        public String userEMail;
//        public String userPassword;
        public String userName;
    }
}
