package com.example.scheduledevelop.service;

import com.example.scheduledevelop.config.PasswordEncoder;
import com.example.scheduledevelop.dto.member.MemberResponseDto;
import com.example.scheduledevelop.entity.Member;
import com.example.scheduledevelop.exception.LoginFailedException;
import com.example.scheduledevelop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원 가입
    public MemberResponseDto save(String userName, String password, String email) {

        String encodedPassword = passwordEncoder.encode(password);

        Member member = new Member(userName, encodedPassword, email);
        Member savedMember = memberRepository.save(member);

        return new MemberResponseDto(savedMember.getId(), savedMember.getUserName(), savedMember.getEmail());
    }

    // 회원 전체 조회
    public List<MemberResponseDto> findAll() {

        return memberRepository.findAll()
                .stream()
                .map(MemberResponseDto::toDto)
                .toList();
    }

    // 회원 단건 조회
    public MemberResponseDto findById(Long id) {
        Member findMember = memberRepository.findByIdOrElseThrow(id);
        return new MemberResponseDto(findMember.getId(), findMember.getUserName(), findMember.getEmail());
    }

    // 회원 정보 수정
    @Transactional
    public void update(Long id, String userName, String password, String email) {

        Member findMember = memberRepository.findByIdOrElseThrow(id);

        if(!passwordEncoder.matches(password, findMember.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "패스워드가 일치하지 않습니다.");
        }

        if(userName == null){
            userName = findMember.getUserName();
        }else if(email == null){
            email = findMember.getEmail();
        }

        findMember.update(userName, email);

    }

    //회원 비밀번호 변경
    @Transactional
    public void updatePassword(Long id, String oldPassword, String newPassword) {

        Member findMember = memberRepository.findByIdOrElseThrow(id);

        if(!passwordEncoder.matches(oldPassword, findMember.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "패스워드가 일치하지 않습니다.");
        }

        findMember.updatePassword(newPassword);
    }

    // 회원 삭제
    public void delete(Long id) {
        Member findMember = memberRepository.findByIdOrElseThrow(id);
        memberRepository.delete(findMember);
    }

    // 회원 로그인
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
