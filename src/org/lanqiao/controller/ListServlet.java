package org.lanqiao.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.Category;
import org.lanqiao.entity.Goods;
import org.lanqiao.entity.PageInfo;

/**
 * Servlet implementation class ListServlet
 */
@WebServlet(name = "listServlet", urlPatterns = { "/list.do" })
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid = request.getParameter("cid");
		if(cid==null){ //如果类别没输，加载默认类别
			cid="1";
		}
		String pageindex = request.getParameter("pageindex");
		if(pageindex==null){ //默认拿此类别的第1页数据;
			pageindex ="1";
		}
		int pagesize =5;
		
		org.lanqiao.service.GoodsService gs = new org.lanqiao.service.impl.GoodsServiceImpl();
		PageInfo<Goods> pageinfo = gs.goodsList(cid, pagesize,Integer.parseInt(pageindex));
		
		org.lanqiao.service.CategoryService cs =new org.lanqiao.service.impl.CategoryServiceImpl();
		Category cate = cs.getCategoryById(cid);
		request.setAttribute("cate", cate);
		request.setAttribute("pageinfo", pageinfo);
		request.getRequestDispatcher("/WEB-INF/list.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

}
