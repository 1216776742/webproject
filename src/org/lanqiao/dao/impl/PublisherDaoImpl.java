package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.lanqiao.dao.PublisherDao;
import org.lanqiao.entity.Publisher;
import org.lanqiao.util.DBUtil;

public class PublisherDaoImpl implements PublisherDao {
	@Override
	public Publisher get(String pid) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Publisher publisher = null;
		try{
			//1获取连接
			conn = DBUtil.getConnection();
			//2通过连接创建PreparedStatement对象;
			String sql="select * from t_publisher where pid=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, pid);
			//3执行操作
			rs = ps.executeQuery();
			//4取数据;
			if(rs.next()){
				publisher = new Publisher(rs.getString("pid"), rs.getString("pname"));
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
		return publisher;
	}

}
