package com.stackroute.newsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackroute.newsapp.domain.User;

/**
 * @author Adarsh
 *
 */
public interface UserRepository extends JpaRepository<User, String> {

	

	/**
	 * @param userid
	 * @param password
	 * @return
	 */
	User findByUseridAndPassword(String userid, String password);
}