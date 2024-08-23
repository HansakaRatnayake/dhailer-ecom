package com.eCom.dhailer.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtService {


    private final SecretKey secretKey;

    @Autowired
    public JwtService(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    public String extractUserEmail(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public Set<SimpleGrantedAuthority> extractUserAuthorities(String token){
        var authorities  = (List<Map<String,String>>) extractClaim(token, claims -> claims.get("authorities"));
        return authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.get("authority"))).collect(Collectors.toSet());
    }



    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }






}
