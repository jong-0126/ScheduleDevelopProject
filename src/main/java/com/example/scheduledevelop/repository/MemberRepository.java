package com.example.scheduledevelop.repository;


import com.example.scheduledevelop.entity.Member;
import com.example.scheduledevelop.exception.LoginFailedException;
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
        return findByEmailAndPassword(email, password).orElseThrow(() -> new LoginFailedException("아이디 또는 비밀번호를 잘못 입력하셨습니다."));
    }
}
