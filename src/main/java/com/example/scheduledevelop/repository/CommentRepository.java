package com.example.scheduledevelop.repository;

import com.example.scheduledevelop.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByScheduleId(Long scheduleId);

    default Comment findByIdOrElseThrow(Long id){
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 댓글을 찾을 수 없습니다."));
    }
}
