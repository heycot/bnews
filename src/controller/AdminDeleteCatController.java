package controller;

import java.io.IOException;

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

public class AdminDeleteCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public AdminDeleteCatController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(CheckLogin.checkLogin(request, response) == true) {
			try {
				int cid = Integer.parseInt(request.getParameter("cid"));
				CategoryDao cd = new CategoryDao();
				int kq = cd.deleteCat(cid);
				if(kq > 0) {
					response.sendRedirect(request.getContextPath() + "/admin/cat?kq=3");
					return;
				}else {
					response.sendRedirect(request.getContextPath() + "/admin/cat?kq=0");
					return;
				}
			}catch( Exception e) {
				e.printStackTrace();
			}
		} else {
			return;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
