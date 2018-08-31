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


public class AdminAddUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAddUserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Users admin = (Users) session.getAttribute("admin");
		if (admin != null ) {
			if(admin.getId_roles() == 1) {
				RequestDispatcher rd = request.getRequestDispatcher("/admin/user/addUser.jsp");
				rd.forward(request, response);
			}else {
				response.sendRedirect(request.getContextPath() + "/admin");
				return;
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			String username = request.getParameter("username");
			String password = StringLibrary.md5(request.getParameter("password"));
			String cfmpassword = StringLibrary.md5(request.getParameter("confirmPassword"));
			String fullname = request.getParameter("fullname");
			String error = null;
			
			if(!password.equals(cfmpassword)) {
				request.setAttribute("error", "Sai password.");
				RequestDispatcher rd = request.getRequestDispatcher("/admin/user/addUser.jsp");
				rd.forward(request, response);
			}else {
				UsersDao ud = new UsersDao();
				Users user = new Users(0, username, password, fullname, 2);
				int kq = ud.addUser(user);
				if(kq > 0) {
					response.sendRedirect(request.getContextPath()  + "/admin/user?kq=1");
					return;
				}else {
					request.setAttribute("error", "Lỗi. Không thêm được");
					RequestDispatcher rd = request.getRequestDispatcher("/admin/user/addUser.jsp");
					rd.forward(request, response);
				}
			}
		}catch( Exception e) {
			request.setAttribute("error", "Lỗi. Không thêm được");
			RequestDispatcher rd = request.getRequestDispatcher("/admin/user/addUser.jsp");
			rd.forward(request, response);
			e.printStackTrace();
		}
	}

}
