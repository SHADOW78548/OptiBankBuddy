package com.shadow.OptiBankBuddy.jwt;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	

	private String secretKey; 
	
	
	public JwtService() { 
	secretKey = generateSecretKey(); 
	} 
	
	
	
	public String generateSecretKey() { 
		try { 
		KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256"); 
		SecretKey secretKey = keyGen.generateKey(); 
		System.out.println("Secret Key : " + secretKey.toString()); 
		return Base64.getEncoder().encodeToString(secretKey.getEncoded()); 
		
			} catch (NoSuchAlgorithmException e) {
			
				throw new RuntimeException("The Secret Key is unable to generate",e);
			
		}
	} 
	
	
	
	public String generateToken(String username) {
	    Map<String, Object> claims = new HashMap<>();
	    claims.put("username", username);

	    return Jwts.builder()
	        .setClaims(claims)
	        .setSubject(username)
	        .setIssuedAt(new Date(System.currentTimeMillis()))
	        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 3)) // 3 hours
	        .signWith(getKey(), SignatureAlgorithm.HS256)
	        .compact();
	}
	 
	 
	 private Key getKey() { 
		 byte[] keyBytes = Decoders.BASE64.decode(secretKey); 
		 return Keys.hmacShaKeyFor(keyBytes); 
		 }



//	public String extractUserName(String token) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//
//	public boolean validateToken(String token, UserDetails userDetails) {
//		// TODO Auto-generated method stub
//		return false;
//	}
	 
	 private Claims extractAllClaims(String token) { 
		 return Jwts.parserBuilder() 
		 .setSigningKey(getKey()) 
		 .build() 
		 .parseClaimsJws(token) 
		 .getBody(); 
		 } 

	 private <T> T extractClaim(String token, Function<Claims, T> claimResolver) { 
		 final Claims claims = extractAllClaims(token); 
		 return claimResolver.apply(claims); 
		 }
	 
	 public String extractEmail(String token) { 
		 return extractClaim(token, Claims::getSubject); 
		 }
	 
	 private Date extractExpiration(String token) { 
		 return extractClaim(token, Claims::getExpiration); 
		 }
	 
	 private boolean isTokenExpired(String token) { 
		 return extractExpiration(token).before(new Date()); 
		 }
	 
	 public boolean validateToken(String token, UserDetails userDetails) { 
		 final String email = extractEmail(token); 
		 return (email.equals(userDetails.getUsername()) && 
		 !isTokenExpired(token)); 
		 }
}
