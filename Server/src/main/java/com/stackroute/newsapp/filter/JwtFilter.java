package com.stackroute.newsapp.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * @author Adarsh
 *Filter for applying security
 */
public class JwtFilter  extends GenericFilterBean{
	@CrossOrigin(origins = "http://localhost:4200")
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain arg2)
			throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest)req;
		final HttpServletResponse response = (HttpServletResponse)resp;
		final String authHeader = request.getHeader("authorization");
		if("OPTIONS".equals(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
			arg2.doFilter(request, response);
		}else {
			if(authHeader==null||!authHeader.startsWith("Bearer")) {
				throw new ServletException("Missing or invalid Authorization header");
			}
		
		final String token = authHeader.substring(7);
		final Claims claim = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
		
		request.setAttribute("claims", claim);
		arg2.doFilter(request, response);
		}
		
	}

}
