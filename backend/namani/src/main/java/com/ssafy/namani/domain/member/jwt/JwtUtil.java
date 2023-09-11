package com.ssafy.namani.domain.member.jwt;
import antlr.Token;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    private Long jwtAccessExpiration = 36000L;
    private Long jwtRefreshExpiration = 36000L;


    public TokenDto generateTokens(UUID memberId){
        String accessToken = generateAccessToken(memberId);
        String refreshToken = generateRefreshToken(memberId);
        return new TokenDto(accessToken, refreshToken);
    }

    public String generateAccessToken(UUID memberId){
        return generateToken(memberId, jwtAccessExpiration);

    }
    public String generateRefreshToken(UUID memberId){
        return generateToken(memberId, jwtRefreshExpiration);

    }

    //Email을 기반으로 JWT Access Token을 생성
    private String generateToken(UUID memberId, Long expiration ){
        return Jwts.builder()
                .setSubject(String.valueOf(memberId))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date().getTime() + expiration)))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public UUID getMemberIdFromToken(String token){
        return UUID.fromString(Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject());
    }

    public boolean validateToken(String authToken){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJwt(authToken);
            return true;
        }catch (SignatureException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException e){
            return false;
        }
    }

}
