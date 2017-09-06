package org.lanqiao.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "checkEmailServlet", urlPatterns = { "/checkemail.do" })
public class CheckEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		org.lanqiao.service.UserService us = new org.lanqiao.service.impl.UserServiceImpl();
		System.out.println("email");
		if(us.hasemail(email)&&email!=null){
			response.getWriter().write("1");
		}else{
			response.getWriter().write("0");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
