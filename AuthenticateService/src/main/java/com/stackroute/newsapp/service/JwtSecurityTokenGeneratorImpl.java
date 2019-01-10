package com.stackroute.newsapp.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.stackroute.newsapp.domain.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Adarsh
 *
 */
@Service
public class JwtSecurityTokenGeneratorImpl implements SecurityTokenGenerator{

	/* (non-Javadoc)
	 * @see com.stackroute.newsapp.service.SecurityTokenGenerator#generateToken(com.stackroute.newsapp.domain.User)
	 */
	@Override
	public Map<String, String> generateToken(User user) {
		//adding claims for admin user -assumption: as single admin user is only expecting for the system user id of admin user is admin
		//there wnt be any register page to create admin as well
		Claims claims = Jwts.claims().setSubject(user.getUserid());
		if(user.getUserid().equalsIgnoreCase("admin")) {
		claims.put("scopes", "ROLE_ADMIN");
		}else {
			claims.put("scopes", "ROLE_USER");
		}
		
		String jwtToken = "";
		jwtToken= Jwts.builder().setSubject(user.getUserid()).claim("roles", claims).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secretkey").compact();
		Map<String,String> map = new HashMap<>();
		map.put("token", jwtToken);
		map.put("message", "User successfully logged in ");
		return map;
	}

}