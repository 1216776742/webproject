package org.lanqiao.admin.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.lanqiao.entity.Category;
import org.lanqiao.service.CategoryService;
import org.lanqiao.service.impl.CategoryServiceImpl;
import com.google.gson.Gson;
@WebServlet(name = "CateController", urlPatterns = { "/cate.do" })
public class CateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CategoryService cs = new CategoryServiceImpl();
		List<Category> list = cs.categoryList();
		Gson gson = new Gson();
		String json = gson.toJson(list);
		System.out.println(json);
		response.getWriter().write(json);
		
		}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
