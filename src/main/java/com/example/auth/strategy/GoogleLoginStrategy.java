package com.example.auth.strategy;

import org.springframework.stereotype.Component;

@Component
public class GoogleLoginStrategy implements LoginStrategy {
    @Override
    public void login(String userId) {
        System.out.println("Google 로그인 시도: " + userId);
        System.out.println("Google OAuth 인증 진행...");
        System.out.println("Google 로그인 성공!");
    }
} 