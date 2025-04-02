package com.example.scheduledevelop.dto;

import lombok.Getter;

@Getter
public class MemberResponseDto {

    private final Long id;

    private final String userName;

    private final String email;

    public MemberResponseDto(Long id, String userName, String email) {
        this.id = id;
        this.userName = userName;
        this.email = email;
    }
}
