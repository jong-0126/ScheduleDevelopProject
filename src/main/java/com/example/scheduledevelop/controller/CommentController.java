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


    @PostMapping("/{id}/comment")
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
