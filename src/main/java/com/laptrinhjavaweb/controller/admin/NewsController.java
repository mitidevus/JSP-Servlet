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
import com.laptrinhjavaweb.utils.FormUtil;

@WebServlet(urlPatterns = { "/admin-news" })
public class NewsController extends HttpServlet {

	private static final long serialVersionUID = 2686801510274002166L;

	@Inject
	private INewsService newsService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		NewsModel model = FormUtil.toModel(NewsModel.class, request);

		Integer offset = (model.getPage() - 1) * model.getMaxPageItem();
		model.setListResult(newsService.findAll(offset, model.getMaxPageItem()));
		model.setTotalItems(newsService.getTotalItems());
		model.setTotalPages((int) Math.ceil((double) model.getTotalItems() / model.getMaxPageItem()));

		request.setAttribute(SystemConstant.MODEL, model); // Controller sẽ đẩy dữ liệu từ Model ra View
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/news/list.jsp"); // Truyền vào cái view muốn
																							// trả về
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
