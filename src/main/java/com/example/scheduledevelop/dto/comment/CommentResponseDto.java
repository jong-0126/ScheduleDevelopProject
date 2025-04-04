package com.example.scheduledevelop.dto.comment;

import com.example.scheduledevelop.entity.Member;
import com.example.scheduledevelop.entity.Schedule;
import lombok.Getter;

@Getter
public class CommentResponseDto {

    private final Long id;

    private final String comment;

    private final Member memberId;

    private final Schedule scheduleId;

    public CommentResponseDto(Long id, String comment, Member memberId, Schedule scheduleId) {
        this.id = id;
        this.comment = comment;
        this.memberId = memberId;
        this.scheduleId = scheduleId;
    }

}
