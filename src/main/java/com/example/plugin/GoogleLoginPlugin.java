package com.example.plugin;

import org.springframework.stereotype.Component;

@Component
public class GoogleLoginPlugin implements Plugin {
    @Override
    public String getName() { 
        return "google-login"; 
    }

    @Override
    public void initialize() {
        System.out.println("Google 로그인 플러그인 초기화");
    }
    
    @Override
    public String execute() { 
        System.out.println("Google 로그인 시도");
        System.out.println("Google OAuth 인증 진행...");
        return "Google 로그인 성공!"; 
    }
} 