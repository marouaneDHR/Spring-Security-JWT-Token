package com.ssjwttest.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.token.Token;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY =" 357638792F423F4428472B4B6250655368566D597133743677397A2443264629" ;

    public String getUsername(String token){
        return extractClaim(token, Claims::getSubject) ;
    }

    public Date getExpiration(String token){
        return extractClaim(token, Claims::getExpiration) ;
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimResolver){
        Claims claims = extractAllClaims(token) ;
        return claimResolver.apply(claims) ;
    }

    public Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Boolean isExpired(String token){
        return getExpiration(token).before(new Date()) ;
    }

    public Boolean validToken(UserDetails userDetails, String token){
        String username = getUsername(token) ;
        return (username.equals(userDetails.getUsername()) && (!isExpired(token)) ) ;
    }

    public String generateToken(String username){
        return generateToken(null, username) ;
    }

    public String generateToken(
            Map<String, Object> claims ,
            String username
    ){
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() * 1000 * 60 * 24))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String GenerateToken(String username){
        Map<String, Object> claims = new HashMap<>();
        return generateToken(claims, username);
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes) ;
    }

}
