package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.mapper.RowMapper;

public interface GenericDAO<T> {
	<T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters);

	void update(String sql, Object... parameters); // Cập nhật và xóa thì void

	Long insert(String sql, Object... parameters); // Thêm thì trả về id

	int count(String sql, Object... parameters);
}
