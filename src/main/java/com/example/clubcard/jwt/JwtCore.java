package com.example.clubcard.jwt;

import com.example.clubcard.domain.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
@Slf4j
public class JwtCore {
    @Value("${spring.app.secret_key}")
    private String secretKey;

    @Value("${spring.app.lifetime}")
    private Duration lifetime;

    public String extractEmail(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public List<?> extractRoles(String token){
        return extractAllClaims(token).get("role", List.class);
    }

    public String generateToken(User user){
        Map<String, Object> claims = new HashMap<>();
        List<String> role = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        claims.put("role", role);
        claims.put("user_id", user.getId());
        return generateToken(claims, user);
    }

    private String generateToken(Map<String, Object> claims, UserDetails userDetails){
        Date issuedDate = getIssuedDate();
        return Jwts.builder()
                .claims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(issuedDate)
                .setExpiration(getExpirationDate(issuedDate))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Date getExpirationDate(Date issuedDate){
        return new Date(issuedDate.getTime() + lifetime.toMillis());
    }

    private Date getIssuedDate(){
        return new Date(System.currentTimeMillis());
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }
}
