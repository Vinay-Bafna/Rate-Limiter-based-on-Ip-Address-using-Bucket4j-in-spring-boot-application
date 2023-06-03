package com.vbcode.ratelimiterusing.bucket4j.controller;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import io.github.bucket4j.local.LocalBucket;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimiterManager {
    private static final long LIMIT = 10; // Maximum number of requests per time unit
    private static final Duration TIME_UNIT = Duration.ofSeconds(1); // Time unit for rate limiting
    private static final Duration BLOCK_DURATION = Duration.ofMinutes(5); // Duration to block IP address
    private static final Map<String, LocalBucket> rateLimiters = new ConcurrentHashMap<>();
    private static final Map<String, Long> blockedIPs = new ConcurrentHashMap<>();

    public static boolean allowRequest(String ipAddress) {
        if (blockedIPs.containsKey(ipAddress)) {
            long blockEndTime = blockedIPs.get(ipAddress);
            if (blockEndTime > System.currentTimeMillis()) {
                // IP address is still blocked
                return false;
            } else {
                // Unblock the IP address
                blockedIPs.remove(ipAddress);
            }
        }

        LocalBucket rateLimiter = rateLimiters.computeIfAbsent(ipAddress, key ->
                (LocalBucket) Bucket4j.builder()
                        .addLimit(Bandwidth.classic(LIMIT, Refill.intervally(LIMIT, TIME_UNIT)))
                        .build()
                        .asScheduler()
        );

        if (rateLimiter.tryConsume(1)) {
            return true; // Request allowed
        } else {
            // Exceeded rate limit, block the IP address
            long blockEndTime = System.currentTimeMillis() + BLOCK_DURATION.toMillis();
            blockedIPs.put(ipAddress, blockEndTime);
            return false; // Request rejected
        }
    }
}
