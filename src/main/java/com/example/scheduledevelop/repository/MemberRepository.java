package com.example.scheduledevelop.repository;


import com.example.scheduledevelop.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
