package com.example.scheduledevelop.controller;

import com.example.scheduledevelop.dto.member.MemberRequestDto;
import com.example.scheduledevelop.entity.Member;
import com.example.scheduledevelop.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody MemberRequestDto dto, HttpServletRequest request){

        Member member = memberService.login(dto.getEmail(), dto.getPassword());

        HttpSession session = request.getSession(true);
        session.setAttribute("sessionKey", member.getId());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request){

        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
