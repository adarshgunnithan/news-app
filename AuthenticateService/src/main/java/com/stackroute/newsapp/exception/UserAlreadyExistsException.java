package com.stackroute.newsapp.exception;

/**
 * @author Adarsh
 *
 */
@SuppressWarnings("serial")
public class UserAlreadyExistsException extends Exception {
	String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UserAlreadyExistsException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "UserAlreadyExistsException [message=" + message + "]";
	}
}