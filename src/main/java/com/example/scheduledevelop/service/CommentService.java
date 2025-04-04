package com.example.scheduledevelop.service;

import com.example.scheduledevelop.dto.comment.CommentResponseDto;
import com.example.scheduledevelop.entity.Comment;
import com.example.scheduledevelop.entity.Member;
import com.example.scheduledevelop.entity.Schedule;
import com.example.scheduledevelop.repository.CommentRepository;
import com.example.scheduledevelop.repository.MemberRepository;
import com.example.scheduledevelop.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final ScheduleRepository scheduleRepository;

    /**
     * 댓글 저장 서비스 로직
     * @param scheduleId 일정 식별자
     * @param memberId 유저 식별자
     * @param comment 저장될 댓글 내용
     * @return
     */
    public CommentResponseDto save(Long scheduleId, Long memberId, String comment) {

        Member findMember = memberRepository.findByIdOrElseThrow(memberId);
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(scheduleId);

        Comment savedComment = new Comment(comment, findMember, findSchedule);

        commentRepository.save(savedComment);

        return null;
    }

}
