package com.example.scheduledevelop.service;

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

    public MemberResponseDto save(String userName, String password, String email) {

        Member member = new Member(userName, password, email);
        Member savedMember = memberRepository.save(member);

        return new MemberResponseDto(savedMember.getId(), savedMember.getUserName(), savedMember.getEmail());
    }

    public List<MemberResponseDto> findAll() {

        return memberRepository.findAll()
                .stream()
                .map(MemberResponseDto::toDto)
                .toList();
    }

    public MemberResponseDto findById(Long id) {
        Member findMember = memberRepository.findByIdOrElseThrow(id);
        return new MemberResponseDto(findMember.getId(), findMember.getUserName(), findMember.getEmail());
    }

    @Transactional
    public void update(Long id, String userName, String password, String email) {

        Member findMember = memberRepository.findByIdOrElseThrow(id);

        if(!findMember.getPassword().equals(password)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "패스워드가 일치하지 않습니다.");
        }

        if(userName == null){
            userName = findMember.getUserName();
        }else if(email == null){
            email = findMember.getEmail();
        }

        findMember.update(userName, email);

    }

    @Transactional
    public void updatePassword(Long id, String oldPassword, String newPassword) {

        Member findMember = memberRepository.findByIdOrElseThrow(id);

        if(!findMember.getPassword().equals(oldPassword)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "패스워드가 일치하지 않습니다.");
        }

        findMember.updatePassword(newPassword);
    }

    public void delete(Long id) {
        Member findMember = memberRepository.findByIdOrElseThrow(id);
        memberRepository.delete(findMember);
    }
}
