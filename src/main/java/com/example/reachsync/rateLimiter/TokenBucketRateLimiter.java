package com.example.reachsync.rateLimiter;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TokenBucketRateLimiter implements RateLimiter {

    public TokenBucketRateLimiter() {
        this.capacity = 100;
        this.refillRate = 10;
    }

    private static class Bucket {
        private int tokens;
        private final int capacity;
        private final int refillRate;
        private long lastRefillTimestamp;


        public Bucket(int capacity, int refillRate) {
            this.capacity = capacity;
            this.refillRate = refillRate;
            this.tokens = capacity;
            this.lastRefillTimestamp = System.currentTimeMillis();
        }

        public synchronized boolean allowRequest() {
            refill();

            if (tokens > 0) {
                tokens--;
                return true;
            }

            return false;
        }

        private void refill() {
            long now = System.currentTimeMillis();
            long elapsedTime = now - lastRefillTimestamp;

            int tokensToAdd = (int) (elapsedTime / 1000 * refillRate);

            if (tokensToAdd > 0) {
                tokens = Math.min(capacity, tokens + tokensToAdd);
            }

            lastRefillTimestamp = now;

        }
    }


    private  final Map<String, Bucket> bucketMap = new ConcurrentHashMap<>();
    private final int capacity;
    private final int refillRate;

    public TokenBucketRateLimiter(int capacity, int refillRate){
        this.capacity = capacity;
        this.refillRate = refillRate;
    }

    @Override
    public boolean allowRequest(String key) {
        bucketMap.putIfAbsent(key, new Bucket(capacity, refillRate));
        return bucketMap.get(key).allowRequest();

    }

}












