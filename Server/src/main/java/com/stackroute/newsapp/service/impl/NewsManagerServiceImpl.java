package com.stackroute.newsapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.newsapp.domain.News;
import com.stackroute.newsapp.exception.NewsAlreadyExistsException;
import com.stackroute.newsapp.exception.NewsNotFoundException;
import com.stackroute.newsapp.repository.NewsRepo;
import com.stackroute.newsapp.service.NewsManagerService;

/**
 * @author 660186
 *Service Implementation for manage news in local repo by admin user
 */
@Service("newsManagerService")
public class NewsManagerServiceImpl implements NewsManagerService {
	
	@Autowired
	private NewsRepo newsRepo;

	@Override
	public boolean saveNews(News news) throws NewsAlreadyExistsException {
		if (news.getId() != null) {
			final Optional<News> object = newsRepo.findById(news.getNewsId());
			if (object.isPresent()) {
				throw new NewsAlreadyExistsException("Could not save news, News already exists");
			}
		}
		newsRepo.save(news);
		return true;
	}

	@Override
	public News updateNews(News news) throws NewsNotFoundException {
		final News object = newsRepo.findById(news.getNewsId()).orElse(null);
		if (object == null) {
			throw new NewsNotFoundException("Could not save news, News already exists");
		}
		newsRepo.save(news);
		return news;
	}
	

	@Override
	public boolean deleteNewsById(int newsId) throws NewsNotFoundException {
		final News object = newsRepo.findById(newsId).orElse(null);
		if (object == null) {
			throw new NewsNotFoundException("News not found");
		}
		newsRepo.delete(object);
		return true;
	}

	@Override
	public List<News> getAllNews() {
		return (List<News>) newsRepo.findAll();
	}

	@Override
	public List<News> searchByTitle(String searchText) {
		return (List<News>) newsRepo.findBytitleContaining(searchText);
	}

}
