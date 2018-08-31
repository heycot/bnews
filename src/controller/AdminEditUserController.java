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

import library.StringLibrary;
import model.bean.Category;
import model.bean.Users;
import model.dao.CategoryDao;
import model.dao.UsersDao;

public class AdminEditUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminEditUserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Users admin = (Users) session.getAttribute("admin");
		if (admin != null) {
			try {
				int uid = Integer.parseInt(request.getParameter("uid"));
				if (admin.getId() == uid || admin.getId_roles() == 1) {
					UsersDao ud = new UsersDao();
					Users user = ud.getUserById(uid);
					request.setAttribute("user", user);
					RequestDispatcher rd = request.getRequestDispatcher("/admin/user/editUser.jsp");
					rd.forward(request, response);
				
				} else {
					response.sendRedirect(request.getContextPath() + "/admin");
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			int uid = Integer.parseInt(request.getParameter("uid"));
			String password = StringLibrary.md5(request.getParameter("password"));
			String cfmpassword = StringLibrary.md5(request.getParameter("confirmPassword"));
			String fullname = request.getParameter("fullname");
			String error = null;

			if (!password.equals(cfmpassword)) {
				request.setAttribute("error", "Sai password.");
				RequestDispatcher rd = request.getRequestDispatcher("/admin/user/addUser.jsp");
				rd.forward(request, response);
			} else {
				UsersDao ud = new UsersDao();
				Users user = new Users(uid, null, password, fullname, 0);
				int kq = ud.editUser(user);
				if (kq > 0) {
					response.sendRedirect(request.getContextPath() + "/admin/user?kq=2");
					return;
				} else {
					request.setAttribute("error", "Lỗi. Không sửa được");
					RequestDispatcher rd = request.getRequestDispatcher("/admin/user/addUser.jsp");
					rd.forward(request, response);
				}
			}
		} catch (Exception e) {
			request.setAttribute("error", "Lỗi.Vui lòng nhập lại");
			RequestDispatcher rd = request.getRequestDispatcher("/admin/user/addUser.jsp");
			rd.forward(request, response);
			e.printStackTrace();
		}
	}

}
