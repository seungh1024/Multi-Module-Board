package com.seungh1024.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;

public class MemberDto {

    @Getter
    @Builder
    public static class MemberJoinRequest{
        @Email
        @NotBlank
        @Size(max = 50, message = "50자 이하만 가능합니다.")
        private String memberEmail;
        @NotBlank
        @Size(min = 8,max = 20, message = "8자 이상, 20자 이하만 가능합니다.")
        private String memberPassword;
        @NotBlank
        @Size(min = 2,max = 20, message = "2자 이상, 20자 이하만 가능합니다.")
        private String memberName;
    }

    @Getter
    @Builder
    public static class MemberUpdateRequest{
        private int memberId;
        @NotBlank
        @Size(min = 8,max = 20, message = "8자 이상, 20자 이하만 가능합니다.")
        private String memberPassword;
    }

// ===================요청, 응답 구분선 ================

    @Getter
    @Builder
    public static class MemberAllResponse{
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private int memberId;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private String memberEmail;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private String memberName;
    }
}