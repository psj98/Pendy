package com.ssafy.namani.domain.member.jwt;
import antlr.Token;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    private Long jwtAccessExpiration = 36000L;
    private Long jwtRefreshExpiration = 36000L;


    public TokenDto generateTokens(String email){
        String accessToken = generateAccessToken(email);
        String refreshToken = generateRefreshToken(email);
        return new TokenDto(accessToken, refreshToken);
    }

    public String generateAccessToken(String email){
        return generateToken(email, jwtAccessExpiration);

    }
    public String generateRefreshToken(String email){
        return generateToken(email, jwtRefreshExpiration);

    }

    //Email을 기반으로 JWT Access Token을 생성
    private String generateToken(String email, Long expiration ){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date().getTime() + expiration)))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getEmailFromToken(String token){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJwt(token).getBody().getSubject();
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
