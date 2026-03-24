package com.example.reachsync.rateLimiter;

public interface RateLimiter {

    boolean allowRequest(String key);
}
