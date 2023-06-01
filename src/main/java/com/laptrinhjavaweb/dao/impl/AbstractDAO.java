package com.laptrinhjavaweb.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
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

	private void setParameter(PreparedStatement statement, Object... parameters) {
		try {
			for (int i = 0; i < parameters.length; i++) {
				Object parameter = parameters[i];
				int index = i + 1;
				if (parameter instanceof Long) {
					statement.setLong(index, (Long) parameter);
				} else if (parameter instanceof String) {
					statement.setString(index, (String) parameter);
				} else if (parameter instanceof Integer) {
					statement.setInt(index, (Integer) parameter);
				} else if (parameter instanceof Timestamp) {
					statement.setTimestamp(index, (Timestamp) parameter);
				}
				// Không có else if của NULL vì khi trường hợp không có, get ra sẽ bị chết ở
				// ngoài, không vào hàm được.
				// Mặc định tất cả các parameters truyền vào đều phải khác NULL hết
			}
		} catch (SQLException e) {
			e.printStackTrace();
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

			// Tham số truyền vào, set các tham số vào statement để thực thi Query
			setParameter(statement, parameters);

			resultSet = statement.executeQuery(); // Result Set tượng trưng như table, phải chạy từng row để get column
													// ra

			// Sau khi query dữ liệu từ database, sau đó map với Model và trả về kết quả
			while (resultSet.next()) {
				results.add(rowMapper.mapRow(resultSet)); // rowMapper sẽ tạo đối tượng, gán các giá trị vào và trả về
															// đối tượng đó
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

	@Override
	public void update(String sql, Object... parameters) {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql);

			setParameter(statement, parameters);

			statement.executeUpdate();

			connection.commit(); // Nếu bị lỗi sẽ rollback, nếu thành công thì update trong database
		} catch (SQLException e) {
			// Nếu như có thao tác trong SQL nào bị lỗi thì sẽ rollback lại
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

	@Override
	public Long insert(String sql, Object... parameters) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Long id = null;

		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			setParameter(statement, parameters);

			statement.executeUpdate();

			resultSet = statement.getGeneratedKeys(); // Lấy ra id, nhớ thêm Statement.RETURN_GENERATED_KEYS trong chỗ
														// đối tượng PreparedStatement thực thi

			if (resultSet.next()) {
				id = resultSet.getLong(1);
			}
			connection.commit(); // Nếu bị lỗi sẽ rollback, nếu thành công thì update trong database
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
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public int count(String sql, Object... parameters) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		int count = 0;

		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);

			// Tham số truyền vào, set các tham số vào statement để thực thi Query
			setParameter(statement, parameters);

			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}
			return count;
		} catch (SQLException e) {
			return 0;
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
				return 0;
			}
		}
	}
}
