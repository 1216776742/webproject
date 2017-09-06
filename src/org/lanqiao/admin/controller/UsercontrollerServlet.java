package org.lanqiao.admin.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.User;
import org.lanqiao.service.UserService;
import org.lanqiao.service.impl.UserServiceImpl;

import com.google.gson.Gson;

@WebServlet(name = "UsercontrollerServlet", urlPatterns = { "/usercontroller.do" })
public class UsercontrollerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		UserService us = new UserServiceImpl();
		if (type!=null && ("list".equals(type))) {
			List<User> userList = us.getUserList();
			Gson gson = new Gson();
			String json = gson.toJson(userList);
//			System.out.println(json);
			response.getWriter().write(json);
		}
		else if (type!=null && "remove".equals(type)) {
			String userid = request.getParameter("userid");
			if (us.remouser(userid)) {
				response.getWriter().write("1");
			}else if(!us.remouser(userid)){
				response.getWriter().write("0");
			}
		}
		else if (type!=null && "edit".equals(type)) {
//			String userid = request.getParameter("uname");
			String uname = request.getParameter("uname");
			String upassword = request.getParameter("upsw");
			String email = request.getParameter("email");
			String tel = request.getParameter("tel");
			String address = request.getParameter("address");
			String sex = request.getParameter("cc");
			User olduser= us.getUserByLoginId(uname);
			User user =new User(olduser.getUserid(), email, uname, upassword, sex, address, tel, olduser.getUstateid(),olduser.getUroleid());
			us.updateUser(user);
			response.getWriter().write("1");
		}
		else if (type!=null && "add".equals(type)) {
			String uname = request.getParameter("uname");
			String upassword = request.getParameter("upsw");
			String email = request.getParameter("email");
			String tel = request.getParameter("tel");
			String address = request.getParameter("address");
			String sex = request.getParameter("cc");
			if (uname!=""&&upassword!=""&&email!=""&&tel!=""&&address!="") {
				String userid = UUID.randomUUID().toString(); //生成主键值;
				String ustateid="B5868B7A06E54DAEB19658343D3A2B28";//有效;
				String uroleid="116F9526C319462780B9CA72F6BB9B41";//普通用户;
				User user =new User(userid, email, uname, upassword, sex, address, tel, ustateid, uroleid);
				us.insertUser(user);
				response.getWriter().write("1");
			}else {
				response.getWriter().write("0");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
