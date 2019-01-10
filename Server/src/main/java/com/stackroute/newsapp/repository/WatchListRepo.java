package com.stackroute.newsapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.stackroute.newsapp.domain.News;
import com.stackroute.newsapp.domain.WatchListItem;

/**
 * @author 660186
 *	WatchListRepo - User can list,add,delete from watch list using this repo
 */
public interface WatchListRepo  extends JpaRepository<WatchListItem, Integer>{
	
	/**
	 * API to find news by user id
	 * @param news
	 * @param userId
	 * @return
	 */
	WatchListItem findByNewsAndUserId(News news,String userId);
	/**
	 * API to fetch by news by id and user id
	 * @param id
	 * @param userId
	 * @return
	 */
	List<WatchListItem> fetchByIdAndUserId (@Param("id") int id,@Param("userId") String userId);
	/**
	 * API to get all watchlist news of a user
	 * @param userId
	 * @return
	 */
	List<News> getAllWatchListNews (@Param("userId") String userId);
	
	
	/**
	 * API to search news
	 * @param userId
	 * @param searchparam
	 * @return
	 */
	List<News> searchNews (@Param("userId") String userId, @Param("searchparam") String searchparam);
}
