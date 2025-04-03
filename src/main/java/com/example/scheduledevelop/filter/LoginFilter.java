package com.example.scheduledevelop.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {

    private static final String[] WHITE_LIST = {"/", "/members/signup", "/members/login", "/members/logout"};


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        log.info("로그인 필터 로직 실행");

        //WHITE_LIST 포함된 경우 !ture = false 반환
        if(!isWhiteList(requestURI)){

            HttpSession session = httpRequest.getSession(false);

            if(session == null || session.getAttribute("sessionKey") == null){
                throw new RuntimeException("로그인 해주세요");
            }

            //로그인 성공 로직
            log.info("로그인에 성공했습니다.");

        }

        filterChain.doFilter(request, response);
    }

    private boolean isWhiteList(String requestURI){
        //WHITE_LIST 포함된 경우 true 반환
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);

    }
}
