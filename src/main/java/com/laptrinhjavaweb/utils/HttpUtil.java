package com.laptrinhjavaweb.utils;

import java.io.BufferedReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtil {

	private String value;

	public HttpUtil(String value) {
		this.value = value;
	}

	// Có được String value được convert từ JSON qua rồi, sau đó lấy String đó map
	// vô Model
	public <T> T toModel(Class<T> tClass) { // Truyền vào Java Class (Class của Model cần mapping)
		try {
			return new ObjectMapper().readValue(value, tClass); // ObjectMapper của thư viện jackson-databind
			// readValue: từ String chuyển thành Model (tạo Class và mapping value rồi trả về Model đó)
			// writeValue: từ Model chuyển thành JSON
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		return null;
	}

	public static HttpUtil of(BufferedReader reader) {
		// Reader chứa JSON, nên phải chuyển sao đó thành String
		StringBuilder sb = new StringBuilder();
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			System.out.print(e.getMessage());
		}

		return new HttpUtil(sb.toString());
	}
}
