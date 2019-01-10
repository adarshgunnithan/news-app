package com.stackroute.newsapp.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;

@Component
public class UserUtil {

	public boolean isAdmin(HttpServletRequest request){
		boolean isAdmin = false;
		Claims claims =(Claims) request.getAttribute("claims");
		String userRole=(String) ((Map<String, Object>) claims.get("roles")).get("scopes");
		if(userRole.equals("ROLE_ADMIN")){
			return true;
		}
		return false;
	}
}
