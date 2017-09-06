package org.lanqiao.ajax;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.Goods;
import org.lanqiao.entity.KeyWord;

import com.google.gson.Gson;

@WebServlet(name = "SearchServlet", urlPatterns = { "/search.do" })
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String keyword = java.net.URLDecoder.decode(request.getParameter("key"),"UTF-8");
		System.out.println(keyword);
		//根据key返回一个提示列表;
		List<KeyWord> list = search(keyword);
		List<Goods> listgoods = searchgoods(keyword);
		Gson gson = new Gson();
		String json =  gson.toJson(list);
		request.setAttribute("listgoods", listgoods);
		response.setContentType("application/json");
		response.getWriter().write(json);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	private List<Goods> searchgoods(String keyword) {
		// TODO Auto-generated method stub
		List<Goods> listgoods = new ArrayList<Goods>();
		try {
			Connection conn = org.lanqiao.util.DBUtil.getConnection();
			String sql="select * from t_goods where gdesc like ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+ keyword +"%");
			ResultSet rs =  ps.executeQuery();
			Goods goods = null;
			while(rs.next()){
				goods = new Goods(rs.getString("gid"), rs.getString("gtitle"), rs.getString("gauthor"), rs.getDouble("gsaleprice"), rs.getDouble("ginprice"), rs.getString("gdesc"), rs.getString("gimg"), rs.getInt("gclicks"), rs.getString("cid"), rs.getString("pid"));
				listgoods.add(goods);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listgoods;
	}
	public List<KeyWord> search(String key) {
		List<KeyWord> list =new ArrayList<KeyWord>();
		try {
			Connection conn = org.lanqiao.util.DBUtil.getConnection();
			String sql="select t.*, rownum rn from (select * from t_goods where gdesc like ?) t where rownum<6";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+ key +"%");
			ResultSet rs =  ps.executeQuery();
			KeyWord keyword = null;
//			Goods goods = null;
			while(rs.next()){
				keyword = new KeyWord(rs.getString(1), rs.getString(2));
				list.add(keyword);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

}
