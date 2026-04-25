package RateLimiter;

public class Bucket {

    private double tokens;
    private long lastRefillTime;

    Bucket(long tokens) {
        this.tokens = tokens;
        this.lastRefillTime = System.nanoTime();
    }

    public Double getTokens() {
        return tokens;
    }

    public void reduce(){
        tokens--;
    }

    public void checkAndRefill(int refillRate, int capacity) {
        long now = System.nanoTime();

        double elapsedTime = (now - lastRefillTime) / 1000000000.0;

        double tokensToAdd = elapsedTime * refillRate;

        this.tokens = Math.min(capacity, this.tokens + tokensToAdd);

        this.lastRefillTime = now;
    }
}
