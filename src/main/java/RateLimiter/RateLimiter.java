package RateLimiter;

import java.util.HashMap;
import java.util.Map;

public class RateLimiter {

    private final Map<String, Bucket> userBuckets;
    private final int capacity;
    private final int refillRate;

    RateLimiter(final int capacity, final int refillRate) {
        this.capacity = capacity;
        this.userBuckets = new HashMap<>();
        this.refillRate = refillRate;
    }

    public boolean shouldAllow(final String userId) {

        final Bucket userBucket = this.userBuckets.computeIfAbsent(userId,
                k -> new Bucket(capacity));

        userBucket.checkAndRefill(refillRate, capacity);

        if (userBucket.getTokens() > 0) {
            userBucket.reduce();
            return true;
        }

        return false;
    }
}
