package com.stackroute.newsapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.stackroute.newsapp.filter.JwtFilter;

/**
 * @author 660186
 * Main launcher for boot application
 *
 */
@SpringBootApplication
public class NewsAppApplication {

	/**
	 * Main function to launch boot application
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(NewsAppApplication.class, args);
	}
	@SuppressWarnings("unchecked")
	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/api/*");
		return registrationBean;
	}
}
