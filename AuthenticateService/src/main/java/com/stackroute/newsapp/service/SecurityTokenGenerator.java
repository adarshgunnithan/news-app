package com.stackroute.newsapp.service;

import java.util.Map;

import com.stackroute.newsapp.domain.User;

/**
 * @author Adarsh
 *
 */
public interface SecurityTokenGenerator {

	/**
	 * @param user
	 * @return
	 */
	Map<String,String> generateToken(User user);
}