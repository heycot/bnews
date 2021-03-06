package controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import library.CheckLogin;
import library.fileLibrary;
import model.bean.Category;
import model.bean.News;
import model.dao.CategoryDao;
import model.dao.NewsDao;

public class AdminDeleteNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminDeleteNewsController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(CheckLogin.checkLogin(request, response) == true) {
			if (request.getParameter("nid") != null) {
				int id = Integer.parseInt(request.getParameter("nid"));
				
				NewsDao nd = new NewsDao();
				News news = nd.getNewByIdNews(id);
				if( news != null) {
					String fileName = news.getPicture();
					if (!fileName.isEmpty()) {
						String filePath = request.getServletContext().getRealPath("/files") + File.separator + fileName;
						File file = new File( filePath);
						file.delete();
						System.out.println(filePath);
					}
					int kq = nd.deleteNews(news);
					if (kq > 0) {
						response.sendRedirect(request.getContextPath() + "/admin/news?kq=3");
						return;
					} else {
						response.sendRedirect(request.getContextPath() + "/admin/news?kq=0");
						return;
					}
				}
			}
			
		}else {
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
