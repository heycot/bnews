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


public class PublicNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public PublicNewsController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pages = 1;
		int start = 0;
		int count = 5;
		if(request.getParameter("pages") != null) {
			pages = Integer.parseInt(request.getParameter("pages"));
			start = (pages - 1)*5;
		}
		request.setAttribute("pages", String.valueOf(pages));
		NewsDao nd = new NewsDao();
		
		int total = nd.getNews().size();
		request.setAttribute("total", String.valueOf(total));
		
		ArrayList<News> listNews = nd.getNewsLimit(start, count);
		request.setAttribute("listNews", listNews);

		RequestDispatcher rd = request.getRequestDispatcher("/news.jsp");
		rd.forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
