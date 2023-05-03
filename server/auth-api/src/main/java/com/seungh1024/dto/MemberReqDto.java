package com.seungh1024.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;


/*
 * Member Request DTO : 요청 데이터를 받기 위한 DTO
 *
 * @Author 강승훈
 * @Since 2023.03.20
 * */

public class MemberReqDto {

    // 회원가입 폼 dto
    @Getter
    @Builder
    public static class JoinForm{
        @Email
        @NotBlank
        @Size(max = 50, message = "아이디는 50자 이하로 작성해야 합니다.")
        public final String memberEmail;
        @NotBlank
        @Size(min = 8, max = 20, message = "비밀번호는 최소 8이상 20이하로 설정해야 합니다.")
        public final String memberPassword;
        @NotBlank
        @Size(max = 20, message = "이름은 20자 이하로 작성해야 합니다.")
        public final String memberName;
        @Nullable
        @Max(value = 150 , message = "나이가 150을 넘을 순 없습니다")
        public final int memberAge;
    }

    @Getter
    @Builder
    public static class LoginForm{
        @Email
        @NotBlank
        public final String memberEmail;
        @NotBlank
        public final String memberPassword;
    }




}
