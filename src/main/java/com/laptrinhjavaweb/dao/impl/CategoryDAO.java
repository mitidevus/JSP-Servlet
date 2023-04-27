package com.laptrinhjavaweb.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.dao.ICategoryDAO;
import com.laptrinhjavaweb.model.CategoryModel;

public class CategoryDAO implements ICategoryDAO {

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver"); // Dùng để load MySQL Driver, nếu lỗi thì ném ClassNotFoundException, không tìm thấy được Driver (Chưa add trong pom.xml)
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
	public List<CategoryModel> findAll() {
		List<CategoryModel> results = new ArrayList<>();
		
		// Query
		String sql = "SELECT * FROM category";
		
		// Open Connection
		Connection connection = getConnection();
		if (connection != null) {
			
		}
		return results;
	}

}
