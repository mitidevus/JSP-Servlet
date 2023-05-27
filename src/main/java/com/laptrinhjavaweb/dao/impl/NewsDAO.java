package com.laptrinhjavaweb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.laptrinhjavaweb.dao.INewsDAO;
import com.laptrinhjavaweb.mapper.NewsMapper;
import com.laptrinhjavaweb.model.NewsModel;

public class NewsDAO extends AbstractDAO<NewsModel> implements INewsDAO {

	@Override
	public List<NewsModel> findByCategoryId(Long categoryId) {
		String sql = "SELECT * FROM news WHERE categoryId = ?";
		return query(sql, new NewsMapper(), categoryId);
	}

	@Override
	public Long save(NewsModel newsModel) {
		ResultSet resultSet = null;
		Long id = null;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			String sql = "INSERT INTO news (title, content, categoryId) VALUES(?, ?, ?)";
			connection = getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, newsModel.getTitle());
			statement.setString(2, newsModel.getContent());
			statement.setLong(3, newsModel.getCategoryId());
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys(); // Lấy ra id, nhớ thêm Statement.RETURN_GENERATED_KEYS trong chỗ
													  // đối tượng PreparedStatement thực thi
			if (resultSet.next()) {
				id = resultSet.getLong(1);
			}
			connection.commit(); // Nếu như success hết thì commit, nếu như không commit thì không lưu xuống
									// database
			return id;
		} catch (SQLException e) {
			// Nếu như có thao tác trong SQL nào bị lỗi thì sẽ rollback lại
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			return null;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				return null;
			}
		}
	}
}
