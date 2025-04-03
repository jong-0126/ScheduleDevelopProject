package com.example.scheduledevelop.dto.schedule;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleRequestDto {

    @NotNull
    @Size(min = 1, max = 10, message = "제목은 1글자 이상 10글자 내외로 작성해주세요.")
    private final String title;

    @NotNull
    private final String contents;

    public ScheduleRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
