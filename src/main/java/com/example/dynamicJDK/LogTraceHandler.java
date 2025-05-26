package com.example.dynamicJDK;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogTraceHandler implements InvocationHandler {
    private final Object target;
    private final LogTrace logTrace;

    public LogTraceHandler(Object target, LogTrace logTrace) {
        this.target = target;
        this.logTrace = logTrace;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String message = method.getDeclaringClass().getSimpleName() + "." + method.getName();
        logTrace.begin(message);
        
        try {
            Object result = method.invoke(target, args);
            logTrace.end(message);
            return result;
        } catch (Exception e) {
            logTrace.end(message + " [ERROR]");
            throw e;
        }
    }
} 