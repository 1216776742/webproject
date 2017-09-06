package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.lanqiao.dao.OrderDao;
import org.lanqiao.entity.Order;
import org.lanqiao.util.DBUtil;

public class OrderDaoImpl implements OrderDao{

	@Override
	public void insert(Order order) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			//2.创建PreparedStatement对象
			String sql = "insert into t_order values(?,?,?,?)";
		    Date date = new Date();
		    java.sql.Date date2 = new java.sql.Date(date.getTime());
			ps = conn.prepareStatement(sql);
			ps.setString(1, order.getOrderid());
			ps.setString(2, order.getUserid());
			ps.setDouble(3, order.getTotalprice());
			ps.setDate(4, date2);
			//3.执行操作
		    ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}				
		}		
	}
	@Override
	public List<Order> getorder(String userid) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Order> orderlist= new ArrayList<Order>();
		Order order = null;
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			//2.创建PreparedStatement对象
			String sql = "select * from t_order where userid=?";
		    Date date = new Date();
		    java.sql.Date date2 = new java.sql.Date(date.getTime());
			ps = conn.prepareStatement(sql);
			ps.setString(1, userid);
			//3.执行操作
			rs = ps.executeQuery();
			while (rs.next()) {
				order = new Order(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getDate(4)); 
				orderlist.add(order);
				System.out.println(orderlist.get(0).getOrderid());
			}
		} catch (Exception e) {

		}finally{
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}				
		}
		 return orderlist;
	}
}
