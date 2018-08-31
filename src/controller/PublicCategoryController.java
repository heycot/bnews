package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.bean.News;
import model.dao.CategoryDao;
import model.dao.NewsDao;

public class PublicCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PublicCategoryController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pages = 1;
		int start = 0;
		int count = 5;
		if(request.getParameter("pages") != null) {
			pages = Integer.parseInt(request.getParameter("pages"));
			start = (pages -1)*5;
		}
		if(request.getParameter("cid") != null) {
			int cid = Integer.parseInt(request.getParameter("cid"));
			
			CategoryDao cd = new CategoryDao();
			Category cat = cd.getCatById(cid);
			request.setAttribute("cat", cat);

			NewsDao nd = new NewsDao();
			int total = nd.getAllNewByIdCat(cid).size();
			request.setAttribute("total", String.valueOf(total));
			
			ArrayList<News> listNews = nd.getNewByIdCat(cid, start, count);
			request.setAttribute("listNews", listNews);
			
			request.setAttribute("pages", String.valueOf(pages));
			RequestDispatcher dispatcher = request.getRequestDispatcher("/category.jsp");
			dispatcher.forward(request, response);
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
