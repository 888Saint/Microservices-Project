package com.microservices.api.gateway.Api.Gateway;


public class JwtTokenGenerator {
    public static void main(String[] args) {
        JwtUtil jwtUtil = new JwtUtil(); // Ensure JwtUtil is a Spring @Component
        String token = jwtUtil.generateToken("testuser"); // Replace "testuser" with any username
        System.out.println("Generated Token: " + token);
    }
}
