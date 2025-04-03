package com.example.scheduledevelop.dto.member;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class MemberRequestDto {

    @NotNull
    @Size(min = 1, max = 4, message = "이름은 1글자 이상 4글자 이하로 작성해주세요.")
    private final String userName;

    @NotNull
    private final String password;

    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "이메일 형식에 맞지 않습니다.")
    private final String email;

    public MemberRequestDto(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }
}
