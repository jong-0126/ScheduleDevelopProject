package com.example.scheduledevelop.service;

import com.example.scheduledevelop.dto.comment.CommentResponseDto;
import com.example.scheduledevelop.entity.Comment;
import com.example.scheduledevelop.entity.Member;
import com.example.scheduledevelop.entity.Schedule;
import com.example.scheduledevelop.repository.CommentRepository;
import com.example.scheduledevelop.repository.MemberRepository;
import com.example.scheduledevelop.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

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
    @Transactional
    public CommentResponseDto save(Long scheduleId, Long memberId, String comment) {

        Member findMember = memberRepository.findByIdOrElseThrow(memberId);
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(scheduleId);

        Comment comment1 = new Comment(comment, findMember, findSchedule);

        Comment savedComment = commentRepository.save(comment1);

        return new CommentResponseDto(
                savedComment.getId(),
                savedComment.getComment(),
                savedComment.getMember().getId(),
                savedComment.getSchedule().getId()
        );
    }

    public List<CommentResponseDto> findBySchedule(Long scheduleId) {
        List<Comment> comments = commentRepository.findByScheduleId(scheduleId);
        return comments.stream()
                .map(comment -> new CommentResponseDto(
                        comment.getId(),
                        comment.getComment(),
                        comment.getMember().getId(),
                        comment.getSchedule().getId()))
                .collect(Collectors.toList());
    }

    public CommentResponseDto findById(Long id) {
        Comment findComment = commentRepository.findByIdOrElseThrow(id);

        return new CommentResponseDto(
                findComment.getId(),
                findComment.getComment(),
                findComment.getMember().getId(),
                findComment.getSchedule().getId()
        );
    }

    @Transactional
    public CommentResponseDto update(Long id, String comment) {

        Comment findComment = commentRepository.findByIdOrElseThrow(id);
        findComment.update(comment);

        return new CommentResponseDto(
                findComment.getId(),
                findComment.getComment(),
                findComment.getMember().getId(),
                findComment.getSchedule().getId()
        );
    }

    public void delete(Long id, Long memberId) {

        Comment comment = commentRepository.findByIdOrElseThrow(id);

        if(!comment.getMember().getId().equals(memberId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "자신이 작성한 댓글만 삭제할 수 있습니다.");
        }

        commentRepository.delete(comment);
    }
}
