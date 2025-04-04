package com.example.scheduledevelop.controller;

import com.example.scheduledevelop.dto.comment.CommentRequestDto;
import com.example.scheduledevelop.dto.comment.CommentResponseDto;
import com.example.scheduledevelop.service.CommentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /**
     * 댓글 생성 API
     * @param scheduleId 일정 식별자
     * @param requestDto 요청 값(댓글 내용)
     * @param session 세션에 저장되어는 값 가져오기
     * @return
     */
    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentResponseDto> create(
            @PathVariable("id") Long scheduleId,
            @RequestBody CommentRequestDto requestDto,
            HttpSession session
    ){

        Long memberId = (Long) session.getAttribute("sessionKey");

        commentService.save(scheduleId, memberId, requestDto.getComment());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
