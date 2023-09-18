package com.ssafy.namani.domain.jwt.service;

import com.ssafy.namani.domain.jwt.util.JwtUtil;
import com.ssafy.namani.domain.jwt.dto.TokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class JwtServiceImpl implements JwtService {

    private final JwtUtil jwtUtil;

    @Autowired
    public JwtServiceImpl(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public TokenDto generateTokens(UUID memberId) {
        return jwtUtil.generateTokens(memberId);
    }

    @Override
    public UUID getMemberIdFromToken(String token) {
        return jwtUtil.getMemberIdFromToken(token);
    }

    @Override
    public boolean validateToken(String token) {
        return jwtUtil.validateToken(token);
    }
}
