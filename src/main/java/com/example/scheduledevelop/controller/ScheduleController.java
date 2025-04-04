package com.example.scheduledevelop.controller;

import com.example.scheduledevelop.dto.schedule.ScheduleRequestDto;
import com.example.scheduledevelop.dto.schedule.ScheduleResponseDto;
import com.example.scheduledevelop.service.ScheduleService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    /**
     * 일정 생성 API
     * @param requestDto 등록할 일정 정보
     * @param session 현재 로그인(세션에 저장) 되어있는 id 확인
     * @return
     */
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> create(@Validated @RequestBody ScheduleRequestDto requestDto, HttpSession session){

        Long memberId = (Long) session.getAttribute("LOGIN_USER");

        ScheduleResponseDto scheduleResponseDto = scheduleService.create(requestDto.getTitle(), requestDto.getContents(), memberId);

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
    }

    /**
     * 일정 전체 조회 API
     * @return
     */
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll(){

        List<ScheduleResponseDto> scheduleResponseDtoList = scheduleService.findAll();

        return new ResponseEntity<>(scheduleResponseDtoList, HttpStatus.OK);

    }

    /**
     * 일정 선택 조회 API
     * @param id 선택할 일정 id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long id){

        ScheduleResponseDto scheduleResponseDto = scheduleService.findById(id);

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    /**
     * 일정 선택 수정 API
     * @param id 선택할 일정 id
     * @param requestDto 수정할 일정 정보
     * @return
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto){

        scheduleService.update(id, requestDto.getTitle(), requestDto.getContents());

        return new ResponseEntity<>(HttpStatus.OK);

    }

    /**
     * 일정 삭제 API
     * @param id 선택할 일정 id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        scheduleService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
