package controller;

import java.lang.*;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import library.CheckLogin;
import library.fileLibrary;
import model.bean.Category;
import model.bean.News;
import model.bean.Users;
import model.dao.CategoryDao;
import model.dao.NewsDao;

@MultipartConfig
public class AdminAddNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminAddNewsController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(CheckLogin.checkLogin(request, response) == true) {
			CategoryDao cd = new CategoryDao();
			ArrayList<Category> listcat = cd.getCats();
			request.setAttribute("listcat", listcat);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/news/addNews.jsp");
			dispatcher.forward(request, response);
		} else {
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			String name = request.getParameter("tentin");
			int id_cat = Integer.parseInt(request.getParameter("danhmuc"));
			String preview = request.getParameter("mota");
			String detail = request.getParameter("chitiet");

			Part part = request.getPart("hinhanh");
			String fileName = fileLibrary.getFileName(part);
			if (!fileName.isEmpty()) {
				System.out.println(fileName);
				String[] hinhanh = fileName.split("[.]");
				long date = System.currentTimeMillis();
				fileName = date + "." + hinhanh[hinhanh.length - 1];
				System.out.println(fileName);
				String dirPath = request.getServletContext().getRealPath("/files");
				File file = new File(dirPath);
				if (!file.exists()) {
					file.mkdirs();
				}

				String filePath = dirPath + File.separator + fileName;
				part.write(filePath);
				System.out.println(dirPath);
			}

			NewsDao nd = new NewsDao();
			News news = new News(0, name, preview, detail, id_cat, fileName, null, 1);
			int kq = nd.addNews(news);
			if (kq > 0) {
				response.sendRedirect(request.getContextPath() + "/admin/news?kq=1");
			} else {
				request.setAttribute("error", "Lỗi. không thêm được");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/news/addNews.jsp");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
