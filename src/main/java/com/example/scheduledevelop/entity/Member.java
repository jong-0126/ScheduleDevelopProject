package com.example.scheduledevelop.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "member")
public class Member extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    private String eamil;

    public Member() {
    }

    public Member(String userName, String password, String eamil) {
        this.userName = userName;
        this.password = password;
        this.eamil = eamil;
    }
}
