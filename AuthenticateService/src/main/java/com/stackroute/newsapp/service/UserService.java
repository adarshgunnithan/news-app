package com.stackroute.newsapp.service;

import com.stackroute.newsapp.domain.User;
import com.stackroute.newsapp.exception.UserAlreadyExistsException;
import com.stackroute.newsapp.exception.UserNotFoundException;

/**
 * @author Adarsh
 *
 */
public interface UserService {
	/**
	 * @param user
	 * @return
	 * @throws UserAlreadyExistsException
	 */
	boolean saveUser(User user) throws UserAlreadyExistsException;

	/**
	 * @param userId
	 * @param password
	 * @return
	 * @throws UserNotFoundException
	 */
	public User findbyUserIdAndPassword(String userId, String password) throws UserNotFoundException;

}