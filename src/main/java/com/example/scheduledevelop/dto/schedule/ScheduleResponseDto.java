package com.example.scheduledevelop.dto.schedule;

import lombok.Getter;

@Getter
public class ScheduleResponseDto {

    private final Long id;

    private final String title;

    private final String contents;

    private final Long memberId;

    public ScheduleResponseDto(Long id, String title, String contents, Long memberId) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.memberId = memberId;
    }
}
