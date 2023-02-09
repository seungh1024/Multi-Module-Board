package com.seungh1024.controller;

import com.seungh1024.Response;
import com.seungh1024.entity.Member;
import com.seungh1024.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/member")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @PostMapping("/signup")
    public Response<?> signup(@RequestBody Member member){
        boolean result = memberService.createMember(member);
        if(result){
            return new Response<>().builder()
                            .code(200)
                            .message("성공")
                            .data(null)
                            .build();
        }else{
            return new Response<>().builder()
                    .code(400)
                    .message("실패")
                    .data(null)
                    .build();
        }
    }



    @GetMapping("/search/all")
    public Response<?> searchAll(){
        List<Member> result = memberService.allMemberList();
        if(result.size() >0 ){
            return new Response<>().builder()
                    .code(200)
                    .message("성공")
                    .data(result)
                    .build();
        }else{
            return new Response<>().builder()
                    .code(404)
                    .message("찾는 데이터가 없습니다")
                    .data(null)
                    .build();
        }
    }

    @GetMapping("/search/{email}")
    public Response<?> searchByEmail(@PathVariable("email") String email){
        Member member = memberService.findMemberByEmail(email);
        if(member != null){
            return new Response<>().builder()
                    .code(200)
                    .message("성공")
                    .data(member)
                    .build();
        }else{
            return new Response<>().builder()
                    .code(404)
                    .message("검색한 사용자가 없습니다")
                    .build();
        }
    }

    @PatchMapping("/update")
    public Response<?> updateByPk(@RequestBody Member member){
        boolean result = memberService.updateMemberPassword(member.getMemberId(), member.getMemberPassword());
        if(result){
            return new Response<>().builder()
                    .code(200)
                    .message("업데이트 성공")
                    .build();
        }else{
            return new Response<>().builder()
                    .code(400)
                    .message("업데이트 실패")
                    .build();
        }
    }

    @DeleteMapping("/delete/{pk}")
    public Response<?> deleteByPk(@PathVariable("pk") int pk){
        boolean result = memberService.deleteMember(pk);
        if(result){
            return new Response<>().builder()
                    .code(200)
                    .message("삭제 성공")
                    .build();
        }else{
            return new Response<>().builder()
                    .code(400)
                    .message("삭제 실패")
                    .build();
        }
    }

}
