package com.example.scheduledevelop.dto.member;

import lombok.Getter;

@Getter
public class PasswordRequestDto {

    private final String oldPassword;

    private final String newPassword;

    public PasswordRequestDto(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
