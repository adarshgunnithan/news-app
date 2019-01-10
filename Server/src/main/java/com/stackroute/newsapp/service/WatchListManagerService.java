package com.stackroute.newsapp.service;

import java.util.List;

import com.stackroute.newsapp.domain.News;
import com.stackroute.newsapp.exception.NewsAlreadyExistsException;
import com.stackroute.newsapp.exception.NewsNotFoundException;

/**
 * @author 660186
 *Service API for manage news in watch list by  user
 */
public interface WatchListManagerService {
	

	/**
	 * @param news
	 * @return true for successful save , else false
	 * @throws NewsAlreadyExistsException
	 */
	public boolean saveNews(News news) throws NewsAlreadyExistsException;
	/**
	 * @param newsId
	 * @return true for successful delete else false
	 * @throws NewsNotFoundException
	 */
	public boolean deleteNewsById(int newsId,String userId) throws NewsNotFoundException;
	
	/**
	 * Get all news for user 
	 * @return List<News>
	 */
	public List<News> getAllNews(String userId);
	
	/**
	 * API to get news by title
	 * @param searchText
	 * @return
	 */
	public List<News> searchByTitle(String userId, String searchText);

}
