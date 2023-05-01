package com.laptrinhjavaweb.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.dao.GenericDAO;
import com.laptrinhjavaweb.mapper.RowMapper;

public class AbstractDAO<T> implements GenericDAO<T> {

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // Dùng để load MySQL Driver, nếu lỗi thì ném
														// ClassNotFoundException, không tìm thấy được Driver (Chưa add
														// trong pom.xml)
			String url = "jdbc:mysql://localhost:3306/newservlet";
			String user = "root";
			String password = "123456";
			return DriverManager.getConnection(url, user, password); // Return về Connection
		} catch (ClassNotFoundException | SQLException e) {
			// Nếu chưa có thư viện driver của MySQL sẽ bị ném vào Exception
			return null; // Return về Connection là null
		}
	}

	@Override
	public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {
		List<T> results = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);

			// Set parameters
			setParameter(statement, parameters);

			resultSet = statement.executeQuery(); // Result Set tượng trưng như table, phải chạy từng row để get column
													// ra
			while (resultSet.next()) {
				results.add(rowMapper.mapRow(resultSet));
			}
			return results;
		} catch (SQLException e) {
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

	private void setParameter(PreparedStatement statement, Object... parameters) {
		try {
			for (int i = 0; i < parameters.length; i++) {
				Object parameter = parameters[i];
				int index = i + 1;
				if (parameter instanceof Long) {
					statement.setLong(index, (Long) parameter);
				} else if (parameter instanceof String) {
					statement.setString(index, (String) parameter);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
