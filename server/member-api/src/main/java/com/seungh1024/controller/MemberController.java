package com.seungh1024.controller;

import com.seungh1024.Response;
import com.seungh1024.entity.Member;
import com.seungh1024.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity signup(@RequestBody Member member){
        boolean result = memberService.createMember(member);
        if(result){
            return new ResponseEntity(success(),HttpStatus.OK);
        }else{
            return new ResponseEntity(failure(),HttpStatus.BAD_REQUEST);
        }
    }



    @GetMapping("/search/all")
    public ResponseEntity searchAll(){
        List<Member> result = memberService.allMemberList();
        if(result.size() >0 ){
            return new ResponseEntity(success(result),HttpStatus.OK);
        }else{
            return new ResponseEntity(failure(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search/{email}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity searchByEmail(@PathVariable("email") String email){
        Member member = memberService.findMemberByEmail(email);
        if(member != null){
            return new ResponseEntity(success(member),HttpStatus.OK);
        }else{
            return new ResponseEntity(failure(),HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/update")
    public ResponseEntity updateByPk(@RequestBody Member member){
        boolean result = memberService.updateMemberPassword(member.getMemberId(), member.getMemberPassword());
        if(result){
            return new ResponseEntity(success(),HttpStatus.OK);
        }else{
            return new ResponseEntity(failure(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{pk}")
    public ResponseEntity deleteByPk(@PathVariable("pk") int pk){
        boolean result = memberService.deleteMember(pk);
        if(result){
            return new ResponseEntity(success(),HttpStatus.OK);
        }else{
            return new ResponseEntity(failure(),HttpStatus.BAD_REQUEST);
        }
    }

}
