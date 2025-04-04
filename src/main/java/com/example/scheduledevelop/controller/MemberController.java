package com.example.scheduledevelop.controller;

import com.example.scheduledevelop.dto.member.MemberRequestDto;
import com.example.scheduledevelop.dto.member.MemberResponseDto;
import com.example.scheduledevelop.dto.member.PasswordRequestDto;
import com.example.scheduledevelop.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원가입 API
     * @param requestDto 요청된 회원 정보(이름, email, password)
     * @return
     */
    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> signUp(@Validated @RequestBody MemberRequestDto requestDto){
        MemberResponseDto memberResponseDto = memberService.save(requestDto.getUserName(), requestDto.getPassword(), requestDto.getEmail());

        return new ResponseEntity<>(memberResponseDto, HttpStatus.CREATED);
    }

    /**
     * 전체 회원 조회 API
     * @return
     */
    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> findAll(){

        List<MemberResponseDto> memberResponseDtoList = memberService.findAll();

        return new ResponseEntity<>(memberResponseDtoList, HttpStatus.OK);
    }

    /**
     * 선택 회원 조회 API
     * @param id 선택할 회원 id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> findById(@PathVariable Long id){

        MemberResponseDto memberResponseDto = memberService.findById(id);
        return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);
    }

    /**
     * 회원 정보 수정 API
     * @param id 선택할 회원 id
     * @param requestDto 수정할 회원 정보
     * @return
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody MemberRequestDto requestDto){

        memberService.update(id, requestDto.getUserName(), requestDto.getPassword(),requestDto.getEmail());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 회원 비밀번호 변경 API
     * @param id 선택할 회원 id
     * @param requestDto 변경할 패스워드
     * @return
     */
    @PatchMapping("/{id}/password")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestBody PasswordRequestDto requestDto){

        memberService.updatePassword(id, requestDto.getOldPassword(), requestDto.getNewPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 회원 삭제 API
     * @param id 선택할 회원 id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        memberService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
