package com.example.scheduledevelop.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "member")
public class Member extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;
}
