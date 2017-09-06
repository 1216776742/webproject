package org.lanqiao.service.impl;

import java.util.List;

import org.lanqiao.entity.News;
import org.lanqiao.service.NewsService;

public class NewsServiceImpl implements NewsService{
	org.lanqiao.dao.NewsDao dao = null;
	public NewsServiceImpl() {
		dao = new org.lanqiao.dao.impl.NewsDaoImpl();
	}
	@Override
	public List<News> newsList() {
		// TODO Auto-generated method stub
		return dao.list();
	}
	@Override
	public News getNewsById(String id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}
	@Override
	public boolean addNews(News news) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean delNews(String tid) {
		// TODO Auto-generated method stub
		return false;
	}

}
