package com.revature.services;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.model.Users;
import com.revature.repositories.LoginDao;

/**
 * Servlet implementation class LoginService
 */
public class LoginService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginDao loginDao;
       
    public LoginService() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//forward to the proper .html page.
		request.getRequestDispatcher("login.html").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		response.setContentType("text/html");
		
		Users user = new Users(request.getParameter("username"), request.getParameter("password"));
		
		if(loginDao.validUser(user)) {
			session.setAttribute("username", user.getUsername());
			session.setAttribute("problem", null);	//TODO don't know what this is for.
			if(user.getRole().getRoleId() == 1) {
				response.sendRedirect("employee");
			}else if(user.getRole().getRoleId() == 2) {
				response.sendRedirect("manager");	//TODO left off here, realized I need to do my front end first.
			}
		}
		
		doGet(request, response);
	}

}
