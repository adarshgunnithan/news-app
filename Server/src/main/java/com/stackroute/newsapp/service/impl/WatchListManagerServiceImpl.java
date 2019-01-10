package com.stackroute.newsapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.newsapp.domain.News;
import com.stackroute.newsapp.domain.WatchListItem;
import com.stackroute.newsapp.exception.NewsAlreadyExistsException;
import com.stackroute.newsapp.exception.NewsNotFoundException;
import com.stackroute.newsapp.repository.WatchListRepo;
import com.stackroute.newsapp.service.WatchListManagerService;

/**
 * @author 660186
 *Service Implementation for manage news in watch list by  user
 */
@Service("watchListManagerService")
public class WatchListManagerServiceImpl implements WatchListManagerService {
	final String testUserId = "testid";

	@Autowired
	private WatchListRepo watchListRepo;
	@Override
	public boolean saveNews(News news) throws NewsAlreadyExistsException {
		//String userId= testUserId;
		if (news.getId() != null) {
			final WatchListItem object = watchListRepo.findByNewsAndUserId(news,news.getUserId());
			if (object != null) {
				throw new NewsAlreadyExistsException("Could not save news, News already exists");
			}
		}
		watchListRepo.save(new WatchListItem(news,news.getUserId()));
		return true;
	}

	@Override
	public boolean deleteNewsById(int newsId,String userId) throws NewsNotFoundException {
		final List<WatchListItem> object = watchListRepo.fetchByIdAndUserId(newsId,userId);
		if (object == null) {
			throw new NewsNotFoundException("News not found");
		}
		for(WatchListItem watchListItem :object){
		watchListRepo.delete(watchListItem);
		}
		return true;
	}

	@Override
	public List<News> getAllNews(String userId) {
		return  (List<News>) watchListRepo.getAllWatchListNews(userId);
	}

	@Override
	public List<News> searchByTitle(String userId, String searchText) {
		return  (List<News>) watchListRepo.searchNews(userId,searchText);
	}

}
