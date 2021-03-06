package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.StringLibrary;
import library.activeString;
import model.bean.News;
import model.dao.NewsDao;


public class PublicSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PublicSearchController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
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
//		for(int k = 0;  k < listnews.size(); k++) {
//			boolean check  = false;
//			String a[] = listnews.get(k).getName().split(" ");
//			for(int i = 0; i < a.length; i++) {
//				a[i] = activeString.removeAccent(a[i]);
//				for(int j = 0; j < arrsearch.length; j++) {
//					if( a[i].equalsIgnoreCase(arrsearch[j])) {
//						check = true;
//						break;
//					}
//				}
//				if(check == true) break;
//			}
//			
//			if(check == false)
//				listnews.remove(k);
//		}
//		
		for(News news:listnews) {
			for(int i = 0; i < arrsearch.length; i++) {
				news.setName(activeString.active(news.getName(), arrsearch[i]));
			//	news.setDetail(activeString.active(news.getDetail(), arrsearch[i]));
			}
			System.out.println("ádfghjk       " + news.getName());
		}
		
		int total = listnews.size();
		request.setAttribute("total", String.valueOf(total));
		request.setAttribute("listNewsSearch", listnews);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/news.jsp");
		dispatcher.forward(request, response);
	}

}
