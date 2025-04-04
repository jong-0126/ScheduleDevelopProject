package com.example.scheduledevelop.service;

import com.example.scheduledevelop.config.PasswordEncoder;
import com.example.scheduledevelop.dto.member.MemberResponseDto;
import com.example.scheduledevelop.entity.Member;
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

    /**
     * 회원 가입 서비스 로직
     * @param userName 이름
     * @param password 패스워드
     * @param email 이메일
     * @return
     */
    public MemberResponseDto save(String userName, String password, String email) {

        // 패스워드 암호화
        String encodedPassword = passwordEncoder.encode(password);

        Member member = new Member(userName, encodedPassword, email);
        Member savedMember = memberRepository.save(member);

        return new MemberResponseDto(savedMember.getId(), savedMember.getUserName(), savedMember.getEmail());
    }

    /**
     * 유저 전체 조회 서비스 로직
     * @return
     */
    public List<MemberResponseDto> findAll() {

        return memberRepository.findAll()
                .stream()
                .map(MemberResponseDto::toDto)
                .toList();
    }

    /**
     * 유저 선택 조회 서비스 로직
     * @param id 선택할 유서 id
     * @return
     */
    public MemberResponseDto findById(Long id) {
        Member findMember = memberRepository.findByIdOrElseThrow(id);
        return new MemberResponseDto(findMember.getId(), findMember.getUserName(), findMember.getEmail());
    }

    /**
     * 회원 정보 수정
     * @param id 선택할 유저 id
     * @param userName 수정할 이름
     * @param password 본인확인 패스워드
     * @param email 수정할 이메일
     */
    @Transactional
    public void update(Long id, String userName, String password, String email) {

        Member findMember = memberRepository.findByIdOrElseThrow(id);

        // 패스워드 본인 인증
        if(!passwordEncoder.matches(password, findMember.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "패스워드가 일치하지 않습니다.");
        }

        // 이름 또는 이메일 하나만 수정 가능
        if(userName == null){
            userName = findMember.getUserName();
        }else if(email == null){
            email = findMember.getEmail();
        }

        findMember.update(userName, email);

    }

    /**
     * 패스워드 수정
     * @param id 선택한 유저 id
     * @param oldPassword
     * @param newPassword
     */
    @Transactional
    public void updatePassword(Long id, String oldPassword, String newPassword) {

        Member findMember = memberRepository.findByIdOrElseThrow(id);

        if(!passwordEncoder.matches(oldPassword, findMember.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "패스워드가 일치하지 않습니다.");
        }

        findMember.updatePassword(newPassword);
    }

    /**
     * 유저 삭제 서비스 로직
     * @param id member_id
     */
    public void delete(Long id) {
        Member findMember = memberRepository.findByIdOrElseThrow(id);
        memberRepository.delete(findMember);
    }
}
