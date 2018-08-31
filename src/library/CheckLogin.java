package library;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Users;

public class CheckLogin {
	
	public static boolean checkLogin(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Users admin = (Users) session.getAttribute("admin");
		if (admin == null) {
			try {
			response.sendRedirect(request.getContextPath() + "/login");
			return false;
			}catch( IOException e) {
				e.printStackTrace();
			}
		}
		
		return true;
	}
}
