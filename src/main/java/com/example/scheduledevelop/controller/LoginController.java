package com.example.scheduledevelop.controller;

import com.example.scheduledevelop.dto.member.MemberRequestDto;
import com.example.scheduledevelop.entity.Member;
import com.example.scheduledevelop.service.LoginService;
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

    private final LoginService loginService;

    /**
     * 로그인 API
     * @param dto 이메일과 비밀번호를 입력받음
     * @param request 세션에 id를 저장함
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody MemberRequestDto dto, HttpServletRequest request){

        Member member = loginService.login(dto.getEmail(), dto.getPassword());

        HttpSession session = request.getSession(true);
        session.setAttribute("LOGIN_USER", member.getId());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 로그아웃 API
     * @param request 저장되어있는 세션을 가져옴
     * @return
     */
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request){

        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
