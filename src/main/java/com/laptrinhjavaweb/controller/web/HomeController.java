package com.laptrinhjavaweb.controller.web;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.INewsService;

@WebServlet(urlPatterns = { "/home" })
public class HomeController extends HttpServlet {

	@Inject
	private ICategoryService categoryService;

	@Inject
	private INewsService newsService;

	private static final long serialVersionUID = 2686801510274002166L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long categoryId = 1L; // Là Long để số nguyên không chịu, nên phải thêm chữ L vô
		request.setAttribute("news", newsService.findByCategoryId(categoryId));
		request.setAttribute("categories", categoryService.findAll());
		RequestDispatcher rd = request.getRequestDispatcher("/views/web/home.jsp"); // Truyền vào cái view muốn trả về
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
