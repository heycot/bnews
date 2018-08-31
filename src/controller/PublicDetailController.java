package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.News;
import model.dao.NewsDao;

public class PublicDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicDetailController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getParameter("nid"));
		if (request.getParameter("nid") == null) {
			NewsDao nd = new NewsDao();
			News news = nd.getNewByIdNewDefault();
			request.setAttribute("news", news);

			ArrayList<News> listNews = nd.getNewsSameIdCat(news.getId());
			request.setAttribute("listNews", listNews);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/detail.jsp");
			dispatcher.forward(request, response);
		}else {
			doPost(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("nid") != null) {
			int nid = Integer.parseInt(request.getParameter("nid"));

			NewsDao nd = new NewsDao();
			News news = nd.getNewByIdNews(nid);
			request.setAttribute("news", news);

			ArrayList<News> listNews = nd.getNewsSameIdCat(nid);
			request.setAttribute("listNews", listNews);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/detail.jsp");
		dispatcher.forward(request, response);
	}

}
