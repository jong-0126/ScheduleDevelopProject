package com.example.scheduledevelop.dto;

import com.example.scheduledevelop.entity.Member;
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

    public static MemberResponseDto toDto(Member member){
        return new MemberResponseDto(member.getId(), member.getUserName(), member.getEamil());
    }
}
