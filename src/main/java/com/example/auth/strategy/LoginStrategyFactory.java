package com.example.auth.strategy;

import org.springframework.stereotype.Component;

import com.example.auth.LoginType;
import com.example.auth.strategy.GoogleLoginStrategy;
import com.example.auth.strategy.KakaoLoginStrategy;
import com.example.auth.strategy.LoginStrategy;

import java.util.Map;

@Component
public class LoginStrategyFactory {
    private final Map<LoginType, LoginStrategy> strategies;

    public LoginStrategyFactory(GoogleLoginStrategy googleLoginStrategy,
                              KakaoLoginStrategy kakaoLoginStrategy) {
        this.strategies = Map.of(
            LoginType.GOOGLE, googleLoginStrategy,
            LoginType.KAKAO, kakaoLoginStrategy
        );
    }

    public LoginStrategy getStrategy(LoginType type) {
        LoginStrategy strategy = strategies.get(type);
        if (strategy == null) {
            throw new IllegalArgumentException("지원하지 않는 로그인 타입입니다: " + type);
        }
        return strategy;
    }
} 