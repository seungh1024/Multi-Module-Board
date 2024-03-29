package com.seungh1024.dto;

import com.seungh1024.encrypt.dto.PasswordCheckerDto;
import com.seungh1024.entity.member.Member;
import com.seungh1024.entity.member.MemberInfo;
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
        private final String memberEmail;
        @NotBlank
        @Size(min = 8, max = 20, message = "비밀번호는 최소 8이상 20이하로 설정해야 합니다.")
        private final String memberPassword;
        @NotBlank
        @Size(max = 20, message = "이름은 20자 이하로 작성해야 합니다.")
        private final String memberName;
        @Nullable
        @Max(value = 150 , message = "나이가 150을 넘을 순 없습니다")
        private final int memberAge;

        public MemberInfo toMemberInfo(){
            return new MemberInfo(this.memberAge);
        }

        public Member toMember(String encodedPassword, String salt, MemberInfo memberInfo){
            return new Member(this.getMemberEmail(),encodedPassword,this.memberName,salt,memberInfo);
        }
    }

    @Getter
    @Builder
    public static class LoginForm{
        @Email
        @NotBlank
        public final String memberEmail;
        @NotBlank
        public final String memberPassword;

        public PasswordCheckerDto toPasswordCheckerDto(Member member) {
            return new PasswordCheckerDto(this.memberPassword, member.getMemberPassword(),member.getMemberSalt());
        }
    }




}
