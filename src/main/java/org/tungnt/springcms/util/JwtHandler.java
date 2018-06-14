package org.tungnt.springcms.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

public class JwtHandler {
	
	public static String createToken(String username, String role){
		
		long currentTime = System.currentTimeMillis();
		
		
		long tokenDurationTime = Constants.USER_EXPIRATION_TIME;
		
		if(role.equals(Constants.USER)) {
			tokenDurationTime = Constants.USER_EXPIRATION_TIME;
		}else {
			tokenDurationTime = Constants.ADMIN_EXPIRATION_TIME;
		}
		
		String token = Jwts.builder()
				.setIssuer(Constants.ISSUER)
				.setSubject(username)
				.claim("role", role)
				.setIssuedAt(new Date(currentTime))
				.setExpiration(new Date(currentTime + tokenDurationTime))
				.signWith(SignatureAlgorithm.HS512, Constants.SECRET_KEY).compact();
		return token;
	}
	
	public static Map<String, String> verifyToken(String token) throws SignatureException{
		Claims claims = Jwts.parser().setSigningKey(Constants.SECRET_KEY).parseClaimsJws(token).getBody();
		Map<String, String> result = new HashMap<>();
		result.put("username", claims.getSubject());
		result.put("role", claims.get("role", String.class));
		return result;
	}
}
