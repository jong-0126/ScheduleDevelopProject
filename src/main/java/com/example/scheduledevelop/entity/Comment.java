package com.example.scheduledevelop.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "comment")
public class Comment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public Comment() {
    }

    public Comment(String comment, Member member, Schedule schedule) {
        this.comment = comment;
        this.member = member;
        this.schedule = schedule;
    }

    public void update(String comment){
        this.comment = comment;
    }
}
