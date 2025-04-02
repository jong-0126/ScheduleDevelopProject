package com.example.scheduledevelop.controller;

import com.example.scheduledevelop.dto.MemberRequestDto;
import com.example.scheduledevelop.dto.MemberResponseDto;
import com.example.scheduledevelop.service.MemberService;
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
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberResponseDto> signUp(@RequestBody MemberRequestDto requestDto){

        MemberResponseDto memberResponseDto = memberService.save(requestDto.getUserName(), requestDto.getPassword(), requestDto.getEmail());

        return new ResponseEntity<>(memberResponseDto, HttpStatus.CREATED);

    }
}
