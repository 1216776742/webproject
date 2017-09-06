package org.lanqiao.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.CookieItem;
import org.lanqiao.entity.User;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
@WebServlet(name = "loginServlet", urlPatterns = { "/login.do" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
//		System.out.println(type);
		if ("cate".equals(type)) {
			String uloginid = request.getParameter("uloginid");
			String upassword = request.getParameter("upassword");
			org.lanqiao.service.UserService us =new org.lanqiao.service.impl.UserServiceImpl();
			User user = us.login(uloginid, upassword);
			if(user!=null){
				//验证通过
				request.getSession().setAttribute("user", user);
				//处理记住密码;
				String chk = request.getParameter("chk");
				if(chk!=null){ //用户选择记住密码;
					//直接将账号写入cookie;
					Cookie cookie =new Cookie("uloginid",uloginid);
					cookie.setMaxAge(60*60*24*7);
					response.addCookie(cookie);
				}
				request.getRequestDispatcher("/WEB-INF/order.jsp").forward(request, response);
			}else {
				request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
			}
		} else if ("loginout".equals(type)) {
			Cookie[] cookies = request.getCookies();
			Cookie currentCookie = null;
			if (cookies != null) {
				for (Cookie c : cookies) {
					if (c.getName().equals("uloginid")) {
						currentCookie = c;
						break;
					}
				}
				if (currentCookie != null) {
					currentCookie.setMaxAge(0);
					response.addCookie(currentCookie);
				}
			}
			request.getSession().removeAttribute("user");
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
