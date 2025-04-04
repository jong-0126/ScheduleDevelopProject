package com.example.scheduledevelop.dto.comment;

import lombok.Getter;

@Getter
public class CommentResponseDto {

    private final Long id;

    private final String comment;

    private final Long memberId;

    private final Long scheduleId;

    public CommentResponseDto(Long id, String comment, Long memberId, Long scheduleId) {
        this.id = id;
        this.comment = comment;
        this.memberId = memberId;
        this.scheduleId = scheduleId;
    }

}
