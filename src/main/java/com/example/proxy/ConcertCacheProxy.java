package com.example.proxy;

import java.util.concurrent.ConcurrentHashMap;

public class ConcertCacheProxy implements Subject {
    private final Subject realSubject;
    private final ConcurrentHashMap<Long, Concert> cache;

    public ConcertCacheProxy(Subject realSubject) {
        this.realSubject = realSubject;
        this.cache = new ConcurrentHashMap<>();
    }

    @Override
    public Concert getConcert(Long id) {
        Concert concert = cache.get(id);
        if (concert == null) {
            concert = realSubject.getConcert(id);
            cache.put(id, concert);
        }
        return concert;
    }
} 