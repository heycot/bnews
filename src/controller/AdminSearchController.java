package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.CheckLogin;
import library.StringLibrary;
import library.activeString;
import model.bean.News;
import model.dao.NewsDao;


public class AdminSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminSearchController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(CheckLogin.checkLogin(request, response) == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/news/index.jsp");
			dispatcher.forward(request, response);
		}else {
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String search = request.getParameter("search");
		String[] arrsearch = search.split(" ");
		for(int i = 0; i < arrsearch.length; i++) {
			arrsearch[i] = activeString.removeAccent(arrsearch[i]);
		}
		
		NewsDao nd = new NewsDao();
		ArrayList<News> listnews = nd.getNewsSearch(arrsearch);

		for(News news:listnews) {
			for(int i = 0; i < arrsearch.length; i++) {
				news.setName(activeString.active(news.getName(), arrsearch[i]));
			}
			System.out.println("ádfghjk       " + news.getName());
		}
		
		int total = listnews.size();
		request.setAttribute("total", String.valueOf(total));
		request.setAttribute("listNewsSearch", listnews);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/news/index.jsp");
		dispatcher.forward(request, response);
	}

}
