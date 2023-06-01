package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.model.NewsModel;

public interface INewsDAO extends GenericDAO<NewsModel> {
	List<NewsModel> findAll(Integer offset, Integer limit);

	NewsModel findOne(Long id);

	List<NewsModel> findByCategoryId(Long categoryId);

	Long save(NewsModel newsModel);

	void update(NewsModel updateNews);

	void delete(long id);

	int getTotalItems();
}
