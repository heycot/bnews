package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import library.CheckLogin;
import model.bean.Users;

public class AdminIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public AdminIndexController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(CheckLogin.checkLogin(request, response) == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/index/index.jsp");
			dispatcher.forward(request, response);
		}else {
			return;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
