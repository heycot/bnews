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


public class AdminAddCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAddCatController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException {
		if(CheckLogin.checkLogin(request, response) == false) {
			return;
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/addCat.jsp");
			rd.forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		if(request.getParameter("tendanhmuc") != null) {
			String name = request.getParameter("tendanhmuc");
			Category cat = new Category(0, name);
			CategoryDao cd = new CategoryDao();
			int kq = cd.addCat(cat);
			if(kq > 0) {
				response.sendRedirect(request.getContextPath()  + "/admin/cat?kq=1");
				return;
			}else {
				request.setAttribute("error", "Lỗi. Không thêm được");
				RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/addCat.jsp");
				rd.forward(request, response);
			}
		}else {
			request.setAttribute("error", "Vui lòng nhập tên danh mục tin");
			RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/addCat.jsp");
			rd.forward(request, response);
		}
	}

}
