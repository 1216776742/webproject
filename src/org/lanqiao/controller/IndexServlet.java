package org.lanqiao.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.News;
import org.lanqiao.entity.User;
@WebServlet(name = "index", urlPatterns = { "/index.do" })
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		org.lanqiao.service.NewsService ns =new org.lanqiao.service.impl.NewsServiceImpl();
		List<News> news = ns.newsList();
		Cookie[] cookies = request.getCookies();
		Cookie currentCookie = null;
		if (cookies!=null) {
			for(Cookie c:cookies){
    			if(c.getName().equals("uloginid")){
    				currentCookie = c;
    				String uloginid = currentCookie.getValue();
    				org.lanqiao.service.UserService us =new org.lanqiao.service.impl.UserServiceImpl();
    				User user = us.getUserByLoginId(uloginid);
    				request.getSession().setAttribute("user", user);
    				break;
    			}
    		}
		}
		request.setAttribute("news", news);
		request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
