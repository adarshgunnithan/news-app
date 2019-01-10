package com.stackroute.newsapp.controller;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.newsapp.domain.News;
import com.stackroute.newsapp.exception.NewsAlreadyExistsException;
import com.stackroute.newsapp.exception.NewsNotFoundException;
import com.stackroute.newsapp.service.NewsManagerService;
import com.stackroute.newsapp.util.UserUtil;

/**
 * @author Adarsh
 * Controller used to expose services for new repo
 * Used by admin for add , update and delete news
 */
@RestController
@RequestMapping("api/v1/news")
@CrossOrigin(origins = "http://localhost:4200")
public class NewsAppController {
	
	@Autowired
	private NewsManagerService newsManagerServiceImpl;
	
	@Autowired
	private UserUtil userUtil;
	
	@PostMapping
	public ResponseEntity<?>saveNews(@RequestBody News news, HttpServletRequest request) {
		ResponseEntity<?> responseEntity = null;
		if(userUtil.isAdmin(request)){
		try {
			newsManagerServiceImpl.saveNews(news);
			responseEntity = new ResponseEntity<News>(news, HttpStatus.CREATED);
		} catch (NewsAlreadyExistsException exception) {
			responseEntity = new ResponseEntity<String>("{ message :" + exception.getMessage() + "}",
					HttpStatus.CONFLICT);
		}
		}else{
			responseEntity = new ResponseEntity<String>("Logged in user doesnt have previlage to access",
					HttpStatus.UNAUTHORIZED);
		}
		return responseEntity;
	}
	
	
	/**
	 * API to update news 
	 * @param id
	 * @param news
	 * @return responseEntity
	 */
	@PutMapping(path = "update/{newsId}")
	public ResponseEntity<?> updateNews(@PathVariable("newsId") Integer id, @RequestBody News news, HttpServletRequest request) {
		ResponseEntity<?> responseEntity = null;
		if(userUtil.isAdmin(request)){
		try {
			News updatedNews = newsManagerServiceImpl.updateNews(news);
			responseEntity = new ResponseEntity<News>(updatedNews, HttpStatus.OK);
		} catch (NewsNotFoundException exception) {
			responseEntity = new ResponseEntity<String>("{ message :" + exception.getMessage() + "}",
					HttpStatus.NOT_FOUND);
		}
		}else{
			responseEntity = new ResponseEntity<String>("Logged in user doesnt have previlage to access",
					HttpStatus.UNAUTHORIZED);
		}
		return responseEntity;
	}

	/**
	 * API to delete news by  id
	 * @param id
	 * @return responseEntity
	 */
	@DeleteMapping(path = "delete/{newsId}")
	public ResponseEntity<?> deleteNews(@PathVariable("newsId") int id, HttpServletRequest request) {
		ResponseEntity<?> responseEntity = null;
		if(userUtil.isAdmin(request)){
			
		try {
			boolean deleteStatus = newsManagerServiceImpl.deleteNewsById(id);
			responseEntity = new ResponseEntity<Boolean>(deleteStatus, HttpStatus.OK);
		} catch (NewsNotFoundException exception) {
			responseEntity = new ResponseEntity<String>("{ message :" + exception.getMessage() + "}",
					HttpStatus.NOT_FOUND);
		}catch(DataIntegrityViolationException e) {
			responseEntity = new ResponseEntity<String>("Unable to delete,news used by user",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}else{
			responseEntity = new ResponseEntity<String>("Logged in user doesnt have previlage to access",
					HttpStatus.UNAUTHORIZED);
		}
		return responseEntity;
	}
	
	/**
	 * API to get all news
	 * @return responseEntity
	 */
	@GetMapping
	public ResponseEntity<?> getAllNews(final ServletRequest req,final ServletResponse res, HttpServletRequest request) {
	
		ResponseEntity<?> responseEntity = null;
		
		List<News> news = newsManagerServiceImpl.getAllNews();
		responseEntity = new ResponseEntity<List<News>>(news, HttpStatus.OK);
	
		return responseEntity;
	}
	

	@GetMapping(path="/search/{searchText}")
	public ResponseEntity<?> searchNews(final ServletRequest req,final ServletResponse res,@PathVariable("searchText")String searchText, HttpServletRequest request) {
		ResponseEntity<?> responseEntity = null;
	
		List<News> news = newsManagerServiceImpl.searchByTitle(searchText);
		responseEntity = new ResponseEntity<List<News>>(news, HttpStatus.OK);
	
		return responseEntity;
	}
	
}
