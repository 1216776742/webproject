package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.dao.OrderDetailDao;
import org.lanqiao.entity.OrderDetail;
import org.lanqiao.util.DBUtil;

public class OrderDetailDaoImpl implements OrderDetailDao {

	@Override
	public void insert(OrderDetail orderDetail) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			//2.创建PreparedStatement对象
			String sql = "insert into t_orderdetail values(?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderDetail.getOrderdetailid());
			ps.setString(2, orderDetail.getGtitle());
			ps.setDouble(3, orderDetail.getGsaleprice());
			ps.setInt(4, orderDetail.getGnumber());
			ps.setString(5, orderDetail.getOrderid());
			ps.setString(6, orderDetail.getGid());
			//3.执行操作
		    ps.executeUpdate();
			
		} catch (Exception e) {
			
		}finally{
			try {
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) {
			}
		}
	}

	@Override
	public List<OrderDetail> getOrderDetail(String orderid) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<OrderDetail> orderdetaillist = new ArrayList<OrderDetail>();
		OrderDetail orderdetail = null;
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			//2.创建PreparedStatement对象
			String sql = "select * t_orderdetail where orderid=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderid);
			//3.执行操作
			rs = ps.executeQuery();
			while(rs.next()){
				orderdetail = new OrderDetail(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getInt(4), rs.getString(5), rs.getString(6));
				orderdetaillist.add(orderdetail);			
			}
		} catch (Exception e) {
			
		}finally{
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) {
			}
		}
		return orderdetaillist;
	}
	
}
