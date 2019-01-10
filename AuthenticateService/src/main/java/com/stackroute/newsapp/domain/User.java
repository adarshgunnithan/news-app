package com.stackroute.newsapp.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Adarsh
 *
 */
@Entity
public class User {

	@JsonProperty("userId")
	@Id
	private String userid;

	private String firtName;

	private String lastName;

	private String password;

	@CreationTimestamp
	private Date created;


	public User() {
		super();
	}

	public User(String userid, String lastName, String password, Date created) {
		super();
		this.setUserid(userid);
		this.setLastName(lastName);
		this.setPassword(password);
		this.created = created;
	
	}

	
	@Override
	public String toString() {
		return "User [userid=" + userid + ", lastName=" + lastName + ", password=" + password + ", created=" + created+ "]";
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getFirtName() {
		return firtName;
	}

	public void setFirtName(String firtName) {
		this.firtName = firtName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}