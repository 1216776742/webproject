//package org.lanqiao.controller;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.lanqiao.entity.CookieItem;
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//@WebServlet(name = "cartServlet", urlPatterns = { "/cart.do" })
//public class CartServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		//1、将商品添加到购物车(cookie)
//		String gid = request.getParameter("gid");
//		CookieItem item = new CookieItem(gid, 1);
//		addItem(item,request,response);
//		//2、获取购物车中所有商品;
//		//3、存到request域对象，转到cart.jsp显示车中的商品
//		request.getRequestDispatcher("/WEB-INF/addsuccess.jsp").forward(request, response);
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
//	}
//	//从购物车(cookie)中取出所有商品;
//	private List<CookieItem> getItems(HttpServletRequest request){
//		Cookie[] cookies=request.getCookies();
//		if(cookies==null) return null;
//		Cookie cart = null;
//		for(Cookie c : cookies){
//			if(c.getName().equals("cart")){
//				cart = c;
//			}
//		}
//		if(cart ==null){
//			return null;
//		}
//		String json =  cart.getValue();
//		Gson gson = new Gson();
//		TypeToken<List<CookieItem>> listType = new TypeToken<List<CookieItem>>() {
//        };
//		List<CookieItem> list = gson.fromJson(json, listType.getType());
//		return list;
//	}
//	//将商品添加到购物车;
//	private void addItem(CookieItem item,HttpServletRequest request,HttpServletResponse response){
//		List<CookieItem> list = getItems(request);
//		 // 第1次向购物车添加商品;
//		if(list==null){
//			list = new ArrayList<CookieItem>();
//			list.add(item);
//		}
//		//表示购物车不为空(有商品)
//		if(list!=null){
//			CookieItem currentItem = null;			
//			for(CookieItem goods : list){
//				if(goods.getGid().equals(item.getGid())){ //存在此商品;
//					currentItem = goods;
//					break;
//				}
//			}
//			if(currentItem==null){ //说明购物车中没有此商品;
//				list.add(item);
//			}else{
//				currentItem.setAmount(currentItem.getAmount()+1); //更新数量;
//			}
//		}
//		//重新将数据写入到cookie;
//		
//		Gson gson = new Gson();
//		String json = gson.toJson(list);
//		Cookie cookie = new Cookie("cart", json);
//		cookie.setMaxAge(60*60*24*365);
//		response.addCookie(cookie);
//		
//	}
//	//从购物车中删除商品;
//	private void removeItem(String gid,HttpServletRequest request,HttpServletResponse response){
//		List<CookieItem> list = getItems(request);
//		if(list==null) return;
//		
//		CookieItem currentItem = null;			
//		for(CookieItem goods : list){
//			if(goods.getGid().equals(gid)){ //存在此商品;
//				currentItem = goods;
//				break;
//			}
//		}
//		if(currentItem==null){ //说明购物车中没有此商品;
//			return;
//		}else{
//			list.remove(currentItem);
//		}
//		//重新写入cookie;
//		Gson gson = new Gson();
//		String json = gson.toJson(list);
//		Cookie cookie = new Cookie("cart", json);
//		cookie.setMaxAge(60*60*24*365);
//		response.addCookie(cookie);
//	}
//
//}




package org.lanqiao.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.Cart;
import org.lanqiao.entity.CookieItem;
import org.lanqiao.entity.Order;
import org.lanqiao.entity.OrderDetail;
import org.lanqiao.entity.User;
import org.lanqiao.service.OrderDetailService;
import org.lanqiao.service.OrderService;
import org.lanqiao.service.impl.OrderDetailServiceImpl;
import org.lanqiao.service.impl.OrderServiceImpl;
import org.lanqiao.util.CartUtil;

import sun.jdbc.odbc.OdbcDef;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
@WebServlet(name = "cartServlet", urlPatterns = { "/cart.do" })
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String gid = request.getParameter("gid");
		if (type==null) {
			request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
		}
		if (type!=null&&type.equals("buy")) {
			//1将商品添加到购物车(cookie)|更新购物车的商品；
			CookieItem item = new CookieItem(gid, 1);
			addItem(item, request, response);
			//2、获取购物车中所有的商品；
			//3、商品存到request域对象，转到cart.jsp显示车中的商品；
			request.getRequestDispatcher("WEB-INF/addsuccess.jsp").forward(request, response);
		}
		if (type!=null&&type.equals("remove")) {
			removeItem(gid, request, response);
			request.getRequestDispatcher("WEB-INF/cart.jsp").forward(request, response);
		}
		if (type!=null&&type.equals("ordersuccess")) {
	    	//1.下订单(取出数据)
	    	List<CookieItem> list = getItems(request);
	    	List<Cart> buygoods = CartUtil.convertCookieItemListToCartList(list);
	    	String orderid = UUID.randomUUID().toString();
	    	String uesrid = ((User)request.getSession().getAttribute("user")).getUserid();
	    	double totalprice = 0;
	    	for( int i = 0;i<buygoods.size();i++){
	    		Cart cart = buygoods.get(i);
	    		totalprice += cart.getAmount()*cart.getGsaleprice();
	    	}
	    	Order order = new Order(orderid, uesrid, totalprice);
	    	OrderService os = new OrderServiceImpl();
	    	os.insertOrder(order);
	    	
	    	for(Cart c:buygoods){
	    		String orderdetailid = UUID.randomUUID().toString();
	    		String gtitle = c.getGtitle();
	    		String gid2 = c.getGid();
	    		Double gsaleprice = c.getGsaleprice();
	    		int gnumber = c.getAmount();
	    		OrderDetail orderDetail = new OrderDetail(orderdetailid, gtitle, gsaleprice, gnumber, orderid,gid2);
	    	    OrderDetailService od = new OrderDetailServiceImpl();
	    	    od.insertOrderDetail(orderDetail);
	    	}
	    	
	    	//2.清空购物车
	    	Cookie[]cookies = request.getCookies();
	    	Cookie currentCookie = null;
	    	if(cookies!=null){
	    		for(Cookie c:cookies){
	    			if(c.getName().equals("cart")){
	    				currentCookie = c;
	    				break;
	    			}
	    		}
	    		if(currentCookie!=null){
		    		currentCookie.setMaxAge(0);
		    		response.addCookie(currentCookie);
		    	}
	    	
	    	}
			request.getRequestDispatcher("WEB-INF/success2.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	//从购物车取出所有的商品
	private List<CookieItem> getItems(HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		Cookie cart = null;
		for (Cookie c : cookies) {
			if (c.getName().equals("cart")) {
				cart = c;
			}
		}
		if(cart==null){
			return null;
		}
		String json = cart.getValue();
		Gson gson = new Gson();
		TypeToken<List<CookieItem>> listType= new TypeToken<List<CookieItem>>(){};
		List<CookieItem> list = gson.fromJson(json, listType.getType());
		return list;
	}
	//将商品添加到购物车
	private void addItem(CookieItem cookieItem,HttpServletRequest request, HttpServletResponse response){
		List<CookieItem> list = getItems(request);
		//第一次向购物车购物。
		if (list==null) {
			list = new ArrayList<CookieItem>();
			list.add(cookieItem);
		}
		//表示购物车不为空（有商品）
		else {
			CookieItem currenItem = null;
			for (CookieItem goods : list) {
				if (goods.getGid().equals(cookieItem.getGid())) {//存在此商品
					currenItem = goods;
					break;
				}
			}
			if (currenItem==null) {//说明购物车中没有此商品
				list.add(cookieItem);
			}
			else {
				currenItem.setAmount(cookieItem.getAmount()+1);
			}
		}
		//重新将数据写入到cookie；
		Gson gson = new Gson();
		String json = gson.toJson(list);
		Cookie cookie = new Cookie("cart", json);
		cookie.setMaxAge(60*60*24*365);
		response.addCookie(cookie);
	}
	//从购物车中删除商品
	private void removeItem(String gid,HttpServletRequest request, HttpServletResponse response){
		List<CookieItem> list = getItems(request);
		if (list==null) return;
		CookieItem currenItem = null;
		for (CookieItem goods : list) {
			if (goods.getGid().equals(gid)) {//存在此商品
				currenItem = goods;
				break;
			}
		}
		if (currenItem==null) {//说明购物车中没有此商品
			return;
		}else{
			list.remove(currenItem);
		}
		//重新写入cookie;
		Gson gson = new Gson();
		String json = gson.toJson(list);
		Cookie cookie = new Cookie("cart", json);
		cookie.setMaxAge(60*60*24*365);
		response.addCookie(cookie);
	}
}
