package com.stackroute.newsapp.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author 660186
 * Domain Object for watch list news
 */
@Entity
@Table(name = "watchlist")
@NamedQueries({
@NamedQuery(name = "WatchListItem.fetchByIdAndUserId",
query = "SELECT w FROM WatchListItem w WHERE w.news.newsId =:id and w.userId=:userId"
),
@NamedQuery(name = "WatchListItem.getAllWatchListNews",
query = "SELECT w.news FROM WatchListItem w WHERE w.userId=:userId"
),
@NamedQuery(name = "WatchListItem.searchNews",
query = "SELECT w.news FROM WatchListItem w WHERE w.userId=:userId and uppercase(w.news.title) like :searchparam"
)
})

public class WatchListItem{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="watch_list_id")
	private int watchListId;
	@OneToOne(cascade = CascadeType.MERGE)
	private News news;
	public WatchListItem() {
		super();
	}
	@Column(name="user_id")
	private String  userId;
	
	
	
	public int getWatchListId() {
		return watchListId;
	}
	public void setWatchListId(int watchListId) {
		this.watchListId = watchListId;
	}
	public News getNews() {
		return news;
	}
	public WatchListItem(News news, String userId) {
		super();
		this.news = news;
		this.userId = userId;
	}
	public void setNews(News news) {
		this.news = news;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
