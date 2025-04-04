package com.example.scheduledevelop.controller;

import com.example.scheduledevelop.dto.comment.CommentRequestDto;
import com.example.scheduledevelop.dto.comment.CommentResponseDto;
import com.example.scheduledevelop.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /**
     * 댓글 작성 API
     * @param scheduleId 일정 id
     * @param requestDto 댓글 내용
     * @param memberId 유저 id
     * @return
     */
    @PostMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<CommentResponseDto> create(
            @PathVariable Long scheduleId,
            @RequestBody CommentRequestDto requestDto,
            @SessionAttribute(name = "LOGIN_USER") Long memberId
            ){

        CommentResponseDto commentResponseDto = commentService.save(scheduleId, memberId, requestDto.getComment());

        return new ResponseEntity<>(commentResponseDto,HttpStatus.CREATED);
    }

    /**
     * 댓글 조회 API
     * @param scheduleId 일정 id
     * @return
     */
    @GetMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<List<CommentResponseDto>> findBySchedule(@PathVariable Long scheduleId){

        List<CommentResponseDto> commentResponseDtoList = commentService.findBySchedule(scheduleId);

        return new ResponseEntity<>(commentResponseDtoList, HttpStatus.OK);
    }


    /**
     * 댓글 단건 조회 API
     * @param id 댓글 id
     * @return
     */
    @GetMapping("/comments/{id}")
    public ResponseEntity<CommentResponseDto> findByOne(@PathVariable Long id){

        CommentResponseDto commentResponseDto = commentService.findById(id);

        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);

    }

    /**
     * 댓글 수정 API
     * @param id 댓글 id
     * @return
     */
    @PutMapping("/comments/{id}")
    public ResponseEntity<CommentResponseDto> update(
            @PathVariable Long id,
            @RequestBody CommentRequestDto requestDto
    ){

        CommentResponseDto commentResponseDto = commentService.update(id, requestDto.getComment());

        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);

    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        commentService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);

    }


}
