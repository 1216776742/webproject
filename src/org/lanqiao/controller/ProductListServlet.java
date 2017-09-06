package org.lanqiao.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.lanqiao.entity.Goods;
import org.lanqiao.entity.PageInfo;

@WebServlet(name = "ProductListServlet", urlPatterns = { "/productList.do" })
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int pagesize =24;
		String pageindex = request.getParameter("pageindex");
		if(pageindex==null){ //默认拿此类别的第1页数据;
			pageindex ="1";
		}
		org.lanqiao.service.GoodsService gs = new org.lanqiao.service.impl.GoodsServiceImpl();
		PageInfo<Goods> pageinfos;
		pageinfos = gs.goodsList(pagesize,Integer.parseInt(pageindex));
		request.setAttribute("pageinfos", pageinfos);
		request.getRequestDispatcher("/WEB-INF/productlist.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
