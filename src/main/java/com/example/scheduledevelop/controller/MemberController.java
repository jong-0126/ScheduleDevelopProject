package com.example.scheduledevelop.controller;

import com.example.scheduledevelop.dto.member.MemberRequestDto;
import com.example.scheduledevelop.dto.member.MemberResponseDto;
import com.example.scheduledevelop.dto.member.PasswordRequestDto;
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

    //회원 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> findById(@PathVariable Long id){

        MemberResponseDto memberResponseDto = memberService.findById(id);
        return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);
    }

    //회원 정보 수정
    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody MemberRequestDto requestDto){

        memberService.update(id, requestDto.getUserName(), requestDto.getPassword(),requestDto.getEmail());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //회원 비밀번호 변경
    @PatchMapping("/{id}/password")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestBody PasswordRequestDto requestDto){

        memberService.updatePassword(id, requestDto.getOldPassword(), requestDto.getNewPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //회원 정보 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        memberService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
