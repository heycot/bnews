package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Users;

public class AdminLogOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminLogOut() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Users admin = (Users) request.getAttribute("admin");
		if( admin == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}else {
		session.invalidate();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
