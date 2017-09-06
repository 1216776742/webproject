package org.lanqiao.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.PasswordAnswer;
import org.lanqiao.entity.User;
import org.lanqiao.util.MailUtil;

/**
 * Servlet implementation class RegeditServlet
 */
@WebServlet(name = "regeditServlet", urlPatterns = { "/regedit.do" })
public class RegeditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//验证 验证码是否正确;
		//从session中取验证与用户输入的验证码进行比对;
		String code = request.getSession().getAttribute("code").toString().toLowerCase();
		String ucheckcode = request.getParameter("ucheckcode").toLowerCase();
		if(!code.equals(ucheckcode)){
			request.getRequestDispatcher("/WEB-INF/regedit.jsp").forward(request, response);
			return;
		}
		//处理注册
		String uloginid = request.getParameter("uname");
		String uemail = request.getParameter("uemail");
		String upassword=request.getParameter("upassword");
		String usex = request.getParameter("usex");
		String utel = request.getParameter("utel");
		String uaddress = request.getParameter("uaddress");
		String userid = UUID.randomUUID().toString(); //生成主键值;
		String ustateid="B5868B7A06E54DAEB19658343D3A2B28";//有效;
		String uroleid="116F9526C319462780B9CA72F6BB9B41";//普通用户;
		
		User user =new User(userid, uemail, uloginid, upassword, usex, uaddress, utel, ustateid, uroleid);
		org.lanqiao.service.UserService us = new org.lanqiao.service.impl.UserServiceImpl();
		us.insertUser(user);
		//密码保护信息 (以下信息用于取回密码以及处理其他帐户问题，请您慎重填写并牢记)
		String squestion = request.getParameter("squestion");
		String sanswer = request.getParameter("sanswer");
		String ubackupemail = request.getParameter("ubackupemail");
		String answerid = UUID.randomUUID().toString();
		
		org.lanqiao.service.PasswordAnswerService pas =new org.lanqiao.service.impl.PasswordAnswerServiceImpl();
		org.lanqiao.entity.PasswordAnswer passwordAnswer =new PasswordAnswer(answerid, squestion, sanswer, ubackupemail, userid);
		pas.insertPasswordAnswer(passwordAnswer);
		
		//发一封激活邮件;
		MailUtil.sendMail("2634325131@qq.com","蓝桥注册激活, ","http://localhost:8080/webproject/enableUser.do?userid="+userid);
		request.getRequestDispatcher("/WEB-INF/regsuccess.jsp").forward(request, response);
	};
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
