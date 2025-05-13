package com.project.move_up_web.securities;

import com.project.move_up_web.entities.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
  private final String secretKey;

  public JwtService() {
    try {
      KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
      SecretKey sk = keyGenerator.generateKey();
      secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }

  private SecretKey getKey() {
    byte[] key = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(key);
  }

  public String generateToken(Users users) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("id", users.getId());
    claims.put("email", users.getEmail());
    return Jwts.builder()
      .claims()
      .add(claims)
      .subject(users.getEmail())
      .issuedAt(new Date(System.currentTimeMillis()))
      .expiration(new Date(System.currentTimeMillis() + 604800 * 1000))
      .and().signWith(getKey()).compact();
  }

  public String extractEmail(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
    final Claims claims = extractAllClaims(token);
    return claimsResolvers.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser()
      .verifyWith(getKey())
      .build()
      .parseSignedClaims(token)
      .getPayload();
  }

  public boolean validateToken(String token, UserDetails userDetails) {
    final String email = extractEmail(token);
    return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }
}
