package com.stackroute.newsapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.newsapp.domain.User;
import com.stackroute.newsapp.exception.UserAlreadyExistsException;
import com.stackroute.newsapp.exception.UserNotFoundException;
import com.stackroute.newsapp.repository.UserRepository;

/**
 * @author Adarsh
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private final transient UserRepository userRepo;

	@Autowired
	public UserServiceImpl(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}


	@Override
	public boolean saveUser(User user) throws UserAlreadyExistsException {
		final Optional<User> object = userRepo.findById(user.getUserid());
		if (object.isPresent()) {
			throw new UserAlreadyExistsException("Could not save User,User already exists!");
		}
		userRepo.save(user);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.stackroute.newsapp.service.UserService#findbyUserIdAndPassword(java.lang.String, java.lang.String)
	 */
	@Override
	public User findbyUserIdAndPassword(String userid, String password) throws UserNotFoundException {
		
		final User user = userRepo.findByUseridAndPassword(userid, password);
		if(user==null) {
			throw new UserNotFoundException("User id and password mismatch");
		}
		return user;
	}
}