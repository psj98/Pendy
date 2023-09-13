package com.ssafy.namani.domain.member.jwt;

import java.util.UUID;

public interface JwtService {
    TokenDto generateTokens(UUID memberId);
    UUID getMemberIdFromToken(String token);  // 반환 타입을 String에서 UUID로 수정
    boolean validateToken(String token);
}
