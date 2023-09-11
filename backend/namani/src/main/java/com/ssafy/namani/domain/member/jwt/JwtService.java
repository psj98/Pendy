package com.ssafy.namani.domain.member.jwt;

public interface JwtService {
    TokenDto generateTokens(String email);
    String getEmailFromToken(String token);
    boolean validateToken(String token);
}
