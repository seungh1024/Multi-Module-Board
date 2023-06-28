package com.seungh1024.controller;


import com.seungh1024.Response;
import com.seungh1024.application.MemberInfoApplication;
import com.seungh1024.dto.MemberReqDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static com.seungh1024.Response.success;

/*
 * 회원 정보 컨트롤러
 *
 *  @Author 강승훈
 *  @Since 2023.06.06
 *
 * */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/member")
public class MemberInfoController {
    private final MemberInfoApplication memberInfoApplication;

    @GetMapping("/info")
    public Response<?> memberInfo(Authentication authentication){
        Long memberId = Long.parseLong(authentication.getName());
        return success(memberInfoApplication.getMemberInfo(memberId));
    }

    @PatchMapping("/info") //일부만 업데이트 -> 비밀번호, 나이  -> PATCH 사용
    public Response<?> updateInfo(@RequestBody @Valid MemberReqDto.InfoDto memberDto, Authentication authentication){
        Long memberId = Long.parseLong(authentication.getName());
        memberInfoApplication.updateMemberInfo(memberDto, memberId);
        return success();
    }
}
