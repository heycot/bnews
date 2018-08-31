package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
public class AdminEditNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminEditNewsController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(CheckLogin.checkLogin(request, response) == true) {
			try {
				CategoryDao cd = new CategoryDao();
				ArrayList<Category> listcat = cd.getCats();
				request.setAttribute("listcat", listcat);

				if (request.getParameter("nid") != null) {
					int nid = Integer.parseInt(request.getParameter("nid"));
					NewsDao nd = new NewsDao();
					News news = nd.getNewByIdNews(nid);
					request.setAttribute("news", news);
				}
			

				RequestDispatcher rd = request.getRequestDispatcher("/admin/news/edit.jsp");
				rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String va[] = {"nid", "tentin", "danhmuc", "mota", "chitiet"};
		boolean check = false;
		for(int i = 0; i < va.length; i++) {
			if(request.getParameter(va[i]) == null || "".equals(request.getParameter(va[i])) ) {
				System.out.println("null " + va[i]);
				check = true;
				//response.sendRedirect(request.getContextPath() + "admin/news/");
				request.setAttribute("error", "Lỗi. không sửa được");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/news/edit.jsp?=nid");
				dispatcher.forward(request, response);
				return;
			}
			System.out.println("OK");
		}
		
		if(check == false) {
		try {
			int nid = Integer.parseInt(request.getParameter("nid"));
			String name = request.getParameter("tentin");
			int id_cat = Integer.parseInt(request.getParameter("danhmuc"));
			String preview = request.getParameter("mota");
			String detail = request.getParameter("chitiet");
			
			NewsDao nd = new NewsDao();
			News news1 = nd.getNewByIdNews(nid);

			Part part = request.getPart("hinhanh");
			String fileName = fileLibrary.getFileName(part);
			if (!fileName.isEmpty()) {
				if("".equals(news1.getPicture())){
					String[] hinhanh = fileName.split("[.]");
					long date = System.currentTimeMillis();
					fileName = date + "." + hinhanh[hinhanh.length - 1];
				}else {
				fileName = news1.getPicture();
				}
				
				String filePath = request.getServletContext().getRealPath("/files") + File.separator + news1.getPicture();
				File file = new File( filePath);
				file.delete();
				
				String dirPath = request.getServletContext().getRealPath("/files");
				file = new File(dirPath);
				if (!file.exists()) {
					file.mkdirs();
				}

				filePath = dirPath + File.separator + fileName;
				System.out.println(filePath);
				System.out.println(fileName);
				part.write(filePath);
				System.out.println(dirPath);
			}

			News news2 = new News(nid, name, preview, detail, id_cat, fileName, null, 0);
			int kq = nd.editNews(news2);
			if (kq > 0) {
				response.sendRedirect(request.getContextPath() + "/admin/news?kq=2");
			} else {
				request.setAttribute("error", "Lỗi. không sửa được");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/news/edit.jsp");
				dispatcher.forward(request, response);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	}

}
