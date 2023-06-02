package com.laptrinhjavaweb.utils;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class FormUtil {
	@SuppressWarnings("unchecked")
	public static <T> T toModel(Class<T> tClass, HttpServletRequest request) {
		T object = null;
		try {
			object = tClass.newInstance();
			// Get các params kiểu key-value (nhưng value không phải giá trị đơn, value là
			// mảng
			// String) trong URL để map vào đối tượng (tự map vào theo kiểu dữ liệu ta mong
			// muốn)
			// VD: Chuyển giá trị key "page" trên URL là String thành giá trị "page" của đối
			// tượng là Integer
			BeanUtils.populate(object, request.getParameterMap());
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			System.out.print(e.getMessage());
		}

		return object;
	}
}
