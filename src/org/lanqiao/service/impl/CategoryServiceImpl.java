package org.lanqiao.service.impl;

import java.util.List;

import org.lanqiao.entity.Category;
import org.lanqiao.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {
	org.lanqiao.dao.CategoryDao dao = null;
	public CategoryServiceImpl() {
		dao = new org.lanqiao.dao.impl.CategoryDaoImpl();
	}
	@Override
	public List<Category> categoryList() {
		// TODO Auto-generated method stub
		return dao.list();
	}
	@Override
	public Category getCategoryById(String cid) {
		// TODO Auto-generated method stub
		return dao.get(cid);
	}

}
