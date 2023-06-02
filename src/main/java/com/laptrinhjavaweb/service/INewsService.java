package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.model.NewsModel;

public interface INewsService {
	List<NewsModel> findAll(Integer offset, Integer limit, String sortName, String sortBy);
	
	List<NewsModel> findByCategoryId(Long categoryId);

	NewsModel save(NewsModel newsModel);

	NewsModel update(NewsModel updateNews);

	void delete(long[] ids);
	
	int getTotalItems();
}
