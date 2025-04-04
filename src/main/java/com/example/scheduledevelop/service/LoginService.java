package com.example.scheduledevelop.service;

import com.example.scheduledevelop.config.PasswordEncoder;
import com.example.scheduledevelop.entity.Member;
import com.example.scheduledevelop.exception.LoginFailedException;
import com.example.scheduledevelop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    /**
     * 로그인 서비스 로직
     * @param email 이메일
     * @param password 패스워드
     * @return
     */
    public Member login(String email, String password) {

        if (email == null || email.isEmpty()) {
            throw new LoginFailedException("아이디 입력은 필수입니다");
        }

        if (password == null || password.isEmpty()) {
            throw new LoginFailedException("비밀번호 입력은 필수입니다");
        }

        return memberRepository.findByEmail(email)
                .filter(member -> passwordEncoder.matches(password, member.getPassword()))
                .orElseThrow(() -> new LoginFailedException("아이디 또는 비밀번호를 잘못 입력하셨습니다."));
    }
}
