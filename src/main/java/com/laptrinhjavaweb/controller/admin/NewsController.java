package com.laptrinhjavaweb.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.service.INewsService;

@WebServlet(urlPatterns = { "/admin-news" })
public class NewsController extends HttpServlet {

	private static final long serialVersionUID = 2686801510274002166L;

	@Inject
	private INewsService newsService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		NewsModel model = new NewsModel();
		model.setListResult(newsService.findAll());
		request.setAttribute(SystemConstant.MODEL, model); // Controller sẽ đẩy dữ liệu từ Model ra View
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/news/list.jsp"); // Truyền vào cái view muốn
																							// trả về
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
