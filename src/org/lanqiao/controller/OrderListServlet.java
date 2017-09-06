package org.lanqiao.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.Order;
import org.lanqiao.entity.OrderDetail;
import org.lanqiao.entity.User;
import org.lanqiao.service.OrderDetailService;
import org.lanqiao.service.OrderService;
import org.lanqiao.service.impl.OrderDetailServiceImpl;
import org.lanqiao.service.impl.OrderServiceImpl;

/**
 * Servlet implementation class OrderListServlet
 */
@WebServlet("/orderlist.do")
public class OrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("search"); 
		
		org.lanqiao.service.UserService us =new org.lanqiao.service.impl.UserServiceImpl();
		User user = us.getUserByLoginId(userid);
		
		OrderService os = new OrderServiceImpl();
		List<Order> orderlist = os.getorder(userid);
		List<OrderDetail> orderdetail = null;
		for (int i = 0; i < orderlist.size(); i++) {
			OrderDetailService ods = new OrderDetailServiceImpl();
			orderdetail = ods.getOrderDetail(orderlist.get(i).getOrderid());
		}
		request.setAttribute("orderlist", orderlist);
		request.setAttribute("user", user);
		request.setAttribute("orderdetail", orderdetail);
		request.getRequestDispatcher("WEB-INF/orderlist.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
