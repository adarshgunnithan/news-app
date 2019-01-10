package com.stackroute.newsapp.exception;

/**
 * @author 660186 Custom exception to be thrown, if news not exists
 */
public class NewsNotFoundException extends Exception {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public NewsNotFoundException(String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String toString() {
		return "NewsNotFoundException [message=" + message + "]";
	}
}
