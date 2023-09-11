package com.ssafy.namani.domain.member.jwt;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class JwtServiceImpl implements JwtService {

    private JwtUtil jwtUtil;

    public JwtServiceImpl(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public TokenDto generateTokens(String email) {
        return jwtUtil.generateTokens(email);
    }

    @Override
    public String getEmailFromToken(String token) {
        return jwtUtil.getEmailFromToken(token);
    }

    @Override
    public boolean validateToken(String token) {
        return jwtUtil.validateToken(token);
    }


}

