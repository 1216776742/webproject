package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.lanqiao.dao.CategoryDao;
import org.lanqiao.entity.Category;
import org.lanqiao.util.DBUtil;

public class CategoryDaoImpl implements CategoryDao {
	@Override
	public List<Category> list() {
		List<Category> list = new ArrayList<Category>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			//1获取连接
			conn = DBUtil.getConnection();
			//2通过连接创建preparedstatement对象;
			String sql="select * from t_category order by ordervalue asc";
			ps = conn.prepareStatement(sql);
			//3执行操作
			rs = ps.executeQuery();
			//4取数据;
			Category cate = null;
			while(rs.next()){
				cate = new Category(rs.getString("cid"), rs.getString("cname"));
				list.add(cate);
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

	@Override
	public Category get(String cid) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Category cate = null;
		try{
			//1获取连接
			conn = DBUtil.getConnection();
			//2通过连接创建preparedstatement对象;
			String sql="select * from t_category where cid=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, cid);
			//3执行操作
			rs = ps.executeQuery();
			//4取数据;
			if(rs.next()){
				cate = new Category(rs.getString("cid"), rs.getString("cname"));
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
		return cate;
	}

}
