package com.seungh1024.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

/*
 * 사용자 요청 DTO
 *
 *  @Author 강승훈
 *  @Since 2023.06.06
 *
 * */
public class MemberReqDto {
    @Getter
    @Builder
    public static class InfoDto{
        @Nullable
        @Size(max = 20, message = "이름은 20자 이하로 작성해야 합니다.")
        public final String memberName;
        @Nullable
        @Size(min = 8, max = 20, message =  "비밀번호는 최소 8이상 20이하로 설정해야 합니다.")
        public final String memberPassword;
        @Nullable
        @Max(value = 150 , message = "나이가 150을 넘을 순 없습니다")
        public final Integer memberAge;
    }
}
