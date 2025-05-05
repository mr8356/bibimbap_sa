package com.example.auth.strategy;

import org.springframework.stereotype.Component;

@Component
public class KakaoLoginStrategy implements LoginStrategy {
    @Override
    public void login(String userId) {
        System.out.println("카카오 로그인 시도: " + userId);
        System.out.println("카카오 OAuth 인증 진행...");
        System.out.println("카카오 로그인 성공!");
    }
} 