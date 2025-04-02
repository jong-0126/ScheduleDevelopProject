package com.example.scheduledevelop.dto;

import lombok.Getter;

@Getter
public class MemberRequestDto {

    private final String userName;

    private final String password;

    private final String email;

    public MemberRequestDto(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }
}
