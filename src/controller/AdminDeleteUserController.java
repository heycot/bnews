package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Category;
import model.bean.Users;
import model.dao.CategoryDao;
import model.dao.UsersDao;

public class AdminDeleteUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public AdminDeleteUserController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Users admin = (Users) session.getAttribute("admin");
		if (admin != null) {
			try {
				int uid = Integer.parseInt(request.getParameter("uid"));
				if(admin.getId_roles() == 1 && uid != 1) {
					UsersDao ud = new UsersDao();
					int kq = ud.deleteUser(uid);
					if(kq > 0) {
						response.sendRedirect(request.getContextPath() + "/admin/user?kq=3");
						return;
					}else {
						response.sendRedirect(request.getContextPath() + "/admin/user?kq=0");
						return;
					}
				}else {
					response.sendRedirect(request.getContextPath() + "/admin");
				}
			}catch( Exception e) {
				e.printStackTrace();
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
