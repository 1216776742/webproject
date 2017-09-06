package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.dao.NewsDao;
import org.lanqiao.entity.News;
import org.lanqiao.util.DBUtil;

public class NewsDaoImpl implements NewsDao {
	//获取所有新闻信息
	@Override
	public List<News> list() {
		List<News> list = new ArrayList<News>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			//1获取连接
			conn = DBUtil.getConnection();
			//2通过连接创建preparedstatement对象;
			String sql="select * from t_news";
			ps = conn.prepareStatement(sql);
			//3执行操作
			rs = ps.executeQuery();
			//4取数据;
			News news = null;
			while(rs.next()){
				news = new News(rs.getString("tid"), rs.getString("title"), rs.getString("tcontent"), rs.getDate("tpubdate"));
				list.add(news);
			}
		}catch(Exception e){
			
		}
		finally{
			//5关闭对象
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	//获取单条新闻信息
	@Override
	public News get(String id) {
		News news = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			//1获取连接
			conn = DBUtil.getConnection();
			//2通过连接创建preparedstatement对象;
			String sql="select * from t_news where tid=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			//3执行操作
			rs = ps.executeQuery();
			//4取数据;
			if(rs.next()){
				news = new News(rs.getString("tid"), rs.getString("title"), rs.getString("tcontent"), rs.getDate("tpubdate"));
			}
		}catch(Exception e){
			
		}
		finally{
			//5关闭对象
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return news;
	}

}
