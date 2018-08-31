package controller;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.CheckLogin;
import model.bean.News;
import model.dao.NewsDao;

public class AdminIndexNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public AdminIndexNewsController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response );
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(CheckLogin.checkLogin(request, response) == true){
			int start = 0;
			int count = 5;
			int pages = 1;
			if(request.getParameter("pages") != null) {
				pages = Integer.parseInt(request.getParameter("pages"));
				start = (pages - 1) * 5;
				
			}
			NewsDao nd = new NewsDao();
			ArrayList<News> listNews = nd.getNews();
			int total = listNews.size();
			
			ArrayList<News> list =  nd.getNewsLimit(start, count);
			request.setAttribute("listNews", list);
			request.setAttribute("total", String.valueOf(total));
			request.setAttribute("pages", String.valueOf(pages));
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/news/index.jsp");
			dispatcher.forward(request, response);
		}else {
			return;
		}
	}

}
