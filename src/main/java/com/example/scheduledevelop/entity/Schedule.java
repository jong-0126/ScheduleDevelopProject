package com.example.scheduledevelop.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String contents;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public Schedule() {

    }

    public Schedule(String title, String contents, Member member) {
        this.title = title;
        this.contents = contents;
        this.member = member;
    }

    public void update(String title, String contents){
        this.title = title;
        this.contents = contents;
    }
}
