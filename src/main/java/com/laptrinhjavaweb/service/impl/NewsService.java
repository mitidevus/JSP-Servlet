package com.laptrinhjavaweb.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.INewsDAO;
import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.service.INewsService;

public class NewsService implements INewsService {

	@Inject
	private INewsDAO newsDao;

	@Override
	public List<NewsModel> findByCategoryId(Long categoryId) {
		return newsDao.findByCategoryId(categoryId);
	}

}
