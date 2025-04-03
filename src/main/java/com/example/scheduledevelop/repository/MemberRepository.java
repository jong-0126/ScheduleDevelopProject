package com.example.scheduledevelop.repository;


import com.example.scheduledevelop.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    default Member findByIdOrElseThrow(Long id){
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id));
    }

    Optional<Member> findByEmailAndPassword(String email, String password);

    default Member findByEmailAndPasswordOrElseThrow(String email, String password){
        return findByEmailAndPassword(email, password).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "이름과 비밀번호가 없습니다."));
    }
}
