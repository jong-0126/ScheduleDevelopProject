package com.example.scheduledevelop.service;

import com.example.scheduledevelop.dto.MemberResponseDto;
import com.example.scheduledevelop.entity.Member;
import com.example.scheduledevelop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResponseDto save(String userName, String password, String email) {

        Member member = new Member(userName, password, email);
        Member savedMember = memberRepository.save(member);

        return new MemberResponseDto(savedMember.getId(), savedMember.getUserName(), savedMember.getEamil());
    }

    public List<MemberResponseDto> findAll() {

        return memberRepository.findAll()
                .stream()
                .map(MemberResponseDto::toDto)
                .toList();
    }
}
