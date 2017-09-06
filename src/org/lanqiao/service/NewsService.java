package org.lanqiao.service;

import java.util.List;

import org.lanqiao.entity.News;

public interface NewsService {
	public List<News> newsList();
	public News getNewsById(String id);
	public boolean addNews(News news);
	public boolean delNews(String tid);
}
