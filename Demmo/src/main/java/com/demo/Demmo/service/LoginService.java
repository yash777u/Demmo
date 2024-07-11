package com.demo.Demmo.service;

import com.demo.Demmo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.time.Duration;
import com.demo.Demmo.respository.UserRepository;

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StringRedisTemplate redisTemplate;

    public String loginAndGetToken(String username, String password) {
        // Check if the token is cached in Redis
        String cachedToken = getCachedToken(username);

        if (cachedToken != null) {
            return cachedToken; // Token is already cached, return it
        }

        // Actual authentication logic (check username and password) here
        User user = userRepository.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            // Generate a random token
            String token = generateRandomToken();

            // Cache the token in Redis with a TTL (e.g., 60 minutes)
            cacheToken(username, token);

            return token; // Return the generated token
        }

        return null; // Authentication failed
    }

    private String getCachedToken(String username) {
        // Retrieve the token from Redis cache
        return redisTemplate.opsForValue().get("token:" + username);
    }

    private void cacheToken(String username, String token) {
        // Cache the token in Redis with a TTL (e.g., 60 minutes)
        redisTemplate.opsForValue().set("token:" + username, token, Duration.ofMinutes(60));
    }

    private String generateRandomToken() {
        // Generate a random UUID-based token (you can use a more secure method)
        return UUID.randomUUID().toString();
    }
}