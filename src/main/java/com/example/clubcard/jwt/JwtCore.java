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
    @Value("${spring.app.access.secret_key}")
    private String accessKey;

    @Value("${spring.app.access.lifetime}")
    private Duration accessLifetime;

    @Value("${spring.app.refresh.secret_key}")
    private String refreshKey;

    @Value("${spring.app.refresh.lifetime}")
    private Duration refreshLifetime;

    public String extractEmail(String token, Boolean isAccess){
        return isAccess ? extractClaim(token, accessKey, Claims::getSubject) :
                extractClaim(token, refreshKey, Claims::getSubject);
    }

    public List<?> extractRoles(String token){
        return extractAllClaims(token, accessKey).get("role", List.class);
    }

    public String generateToken(User user, boolean isAccess){
        Map<String, Object> claims = new HashMap<>();
        List<String> role = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        claims.put("role", role);
        claims.put("user_id", user.getId());

        return isAccess ? generateToken(claims, user, accessKey, accessLifetime) :
                generateToken(claims, user, refreshKey, refreshLifetime);
    }

    private String generateToken(Map<String, Object> claims,
                                 UserDetails userDetails,
                                 String secretKey,
                                 Duration lifetime){
        Date issuedDate = getIssuedDate();
        return Jwts.builder()
                .claims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(issuedDate)
                .setExpiration(getExpirationDate(issuedDate, lifetime))
                .signWith(getSigningKey(secretKey), SignatureAlgorithm.HS256)
                .compact();
    }

    private Date getExpirationDate(Date issuedDate, Duration lifetime){
        return new Date(issuedDate.getTime() + lifetime.toMillis());
    }

    private Date getIssuedDate(){
        return new Date(System.currentTimeMillis());
    }

    private <T> T extractClaim(String token, String secretKey, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token, secretKey);
        return claimsResolvers.apply(claims);
    }

    private Claims extractAllClaims(String token, String secretKey) {
        return Jwts.parser()
                .setSigningKey(getSigningKey(secretKey))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey(String secretKey) {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }
}
