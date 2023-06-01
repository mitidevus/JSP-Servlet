package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.model.NewsModel;

public class NewsMapper implements RowMapper<NewsModel> {

	@Override
	public NewsModel mapRow(ResultSet resultSet) {
		try {
			NewsModel news = new NewsModel();
			news.setId(resultSet.getLong("id"));
			news.setTitle(resultSet.getString("title"));
			news.setContent(resultSet.getString("content"));
			news.setThumbnail(resultSet.getString("thumbnail"));
			news.setCategoryId(resultSet.getLong("categoryId"));
			news.setShortDescription(resultSet.getString("shortDescription"));
			news.setCreatedAt(resultSet.getTimestamp("createdAt"));
			news.setCreatedBy(resultSet.getString("createdBy"));

			if (resultSet.getTimestamp("updatedAt") != null) {
				news.setUpdatedAt(resultSet.getTimestamp("updatedAt"));
			}
			if (resultSet.getString("createdBy") != null) {
				news.setUpdatedBy(resultSet.getString("createdBy"));
			}

			return news;
		} catch (SQLException e) {
			return null;
		}
	}
}
