package com.seungh1024.controller;

import com.seungh1024.Response;
import com.seungh1024.dto.MemberDto;
import com.seungh1024.entity.Member;
import com.seungh1024.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.seungh1024.Response.success;
import static com.seungh1024.Response.failure;

@RestController
@RequestMapping("/api/v1/member")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody @Valid MemberDto.MemberJoinRequest memberDto){
        Member member = memberService.createMember(memberDto);
        MemberDto.MemberAllResponse response = MemberDto.MemberAllResponse.builder()
                .memberId(member.getMemberId())
                .memberEmail(member.getMemberEmail())
                .memberName(member.getMemberName())
                .build();
        return new ResponseEntity(success(response),HttpStatus.OK);
    }



    @GetMapping("/search/all")
    public ResponseEntity searchAll(){
        List<Member> members = memberService.allMemberList();
        List<MemberDto.MemberAllResponse> response = members.stream()
                .map(m -> MemberDto.MemberAllResponse.builder()
                        .memberId((m.getMemberId()))
                        .memberEmail(m.getMemberEmail())
                        .memberName(m.getMemberName())
                        .build())
                        .collect(Collectors.toList());

        return new ResponseEntity(success(response),HttpStatus.OK);
    }

    @GetMapping("/search/{email}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity searchByEmail(@PathVariable("email") String email){
        Member member = memberService.findMemberByEmail(email);
        MemberDto.MemberAllResponse response = MemberDto.MemberAllResponse.builder()
                .memberEmail(member.getMemberEmail())
                .memberName(member.getMemberName())
                .build();
        return new ResponseEntity(success(response),HttpStatus.OK);

    }

    @PatchMapping("/update")
    public ResponseEntity updateByPk(@RequestBody @Valid MemberDto.MemberUpdateRequest memberDto){
        memberService.updateMemberPassword(memberDto.getMemberId(), memberDto.getMemberPassword());
        return new ResponseEntity(success(),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{pk}")
    public ResponseEntity deleteByPk(@PathVariable("pk") int pk){
        memberService.deleteMember(pk);
        return new ResponseEntity(success(),HttpStatus.OK);
    }

}
