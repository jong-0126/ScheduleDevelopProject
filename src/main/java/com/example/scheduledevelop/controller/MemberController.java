package com.example.scheduledevelop.controller;

import com.example.scheduledevelop.dto.MemberRequestDto;
import com.example.scheduledevelop.dto.MemberResponseDto;
import com.example.scheduledevelop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //회원 가입
    @PostMapping
    public ResponseEntity<MemberResponseDto> signUp(@RequestBody MemberRequestDto requestDto){

        MemberResponseDto memberResponseDto = memberService.save(requestDto.getUserName(), requestDto.getPassword(), requestDto.getEmail());

        return new ResponseEntity<>(memberResponseDto, HttpStatus.CREATED);
    }

    //회원 전체 조회
    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> findAll(){

        List<MemberResponseDto> memberResponseDtoList = memberService.findAll();

        return new ResponseEntity<>(memberResponseDtoList, HttpStatus.OK);
    }
}
