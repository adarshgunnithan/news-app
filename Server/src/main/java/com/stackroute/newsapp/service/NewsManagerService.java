package com.stackroute.newsapp.service;

import java.util.List;

import com.stackroute.newsapp.domain.News;
import com.stackroute.newsapp.exception.NewsAlreadyExistsException;
import com.stackroute.newsapp.exception.NewsNotFoundException;

/**
 * @author 660186
 * Service API for manage news in local repo by admin user
 */
public interface NewsManagerService {
	
	/**
	 * @param news
	 * @return true for successful save , else false
	 * @throws NewsAlreadyExistsException
	 */
	public boolean saveNews(News news) throws NewsAlreadyExistsException;
	/**
	 * @param news
	 * @return News for successful update, NewsNotFoundException for news doesnt exits 
	 * @throws NewsNotFoundException
	 */
	public News updateNews(News news) throws NewsNotFoundException;
	/**
	 * @param newsId
	 * @return true for successful delete else false
	 * @throws NewsNotFoundException
	 */
	public boolean deleteNewsById(int newsId) throws NewsNotFoundException;
	
	/**
	 * Get all news from news repo
	 * @return List<News>
	 */
	public List<News> getAllNews();
	
	/**
	 * API to get news by title
	 * @param searchText
	 * @return
	 */
	public List<News> searchByTitle(String searchText);

}
