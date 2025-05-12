package com.example.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth.strategy.LoginService;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        if (request.getLoginType() == LoginType.GOOGLE) {
            System.out.println("Google 로그인 시도: " + request.getUserId());
            System.out.println("Google OAuth 인증 진행...");
            System.out.println("Google 로그인 성공!");
        } else if (request.getLoginType() == LoginType.KAKAO) {
            System.out.println("카카오 로그인 시도: " + request.getUserId());
            System.out.println("카카오 OAuth 인증 진행...");
            System.out.println("카카오 로그인 성공!");
        } else {
            throw new IllegalArgumentException("지원하지 않는 로그인 타입입니다: " + request.getLoginType());
        }
        return "로그인 성공";
    }
} 