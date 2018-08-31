package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import library.CheckLogin;
import model.bean.Category;
import model.bean.Users;
import model.dao.CategoryDao;

public class AdminEditCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminEditCatController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(CheckLogin.checkLogin(request, response) == true){
			try {
				int cid = Integer.parseInt(request.getParameter("cid"));
				CategoryDao cd = new CategoryDao();
				Category cat = cd.getCatById(cid);
				request.setAttribute("cat", cat);
				System.out.println(cat.getName());
				RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/editCat.jsp");
				rd.forward(request, response);
			}catch( Exception e) {
				e.printStackTrace();
			}
		} else {
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		if (request.getParameter("tendanhmuc") != null) {
			try {
				String name = request.getParameter("tendanhmuc");
				int cid = Integer.parseInt(request.getParameter("cid"));
				Category cat = new Category(cid, name);
				CategoryDao cd = new CategoryDao();
				int kq = cd.editCat(cat);
				if (kq > 0) {
					response.sendRedirect(request.getContextPath() + "/admin/cat?kq=1");
					return;
				} else {
					request.setAttribute("error", "Lỗi. Không sửa được");
					RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/editCat.jsp");
					rd.forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			request.setAttribute("error", "Vui lòng nhập tên danh mục tin");
			RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/addCat.jsp");
			rd.forward(request, response);
		}
	}

}
