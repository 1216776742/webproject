package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.dao.GoodsDao;
import org.lanqiao.entity.Goods;
import org.lanqiao.entity.PageInfo;
import org.lanqiao.entity.Publisher;
import org.lanqiao.util.DBUtil;

public class GoodsDaoImpl implements GoodsDao {
	//获取某一类别商品数据;
	@Override
	public PageInfo<Goods> list(String cid,int pagesize, int pageindex) {
		
		PageInfo<Goods> pageinfo = new PageInfo<Goods>();
		List<Goods> list = new ArrayList<Goods>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			//1获取连接
			conn = DBUtil.getConnection();
			//2通过连接创建PreparedStatement对象;
			String sql="select t2.* from (select t1.* ,rownum rn from (select * from t_goods where cid=?) t1 where rownum<=? ) t2 where rn>=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, cid);
			int endindex = pagesize * pageindex;
			int startindex = (pageindex-1)*pagesize +1;
			ps.setInt(2, endindex);
			ps.setInt(3, startindex);
			//3执行操作
			rs = ps.executeQuery();
			//4取数据;
			Goods goods = null;
			org.lanqiao.dao.PublisherDao dao = new org.lanqiao.dao.impl.PublisherDaoImpl();
			while(rs.next()){
				goods = new Goods(rs.getString("gid"), rs.getString("gtitle"), rs.getString("gauthor"), rs.getDouble("gsaleprice"), rs.getDouble("ginprice"), rs.getString("gdesc"), rs.getString("gimg"), rs.getInt("gclicks"), rs.getString("cid"), rs.getString("pid"));
				//处理外键
				Publisher publisher = dao.get(goods.getPid());
				goods.setPublisher(publisher);
				list.add(goods);
			}
			//给pageinfo对象赋值;
			pageinfo.setData(list);
			pageinfo.setIsfirstpage(pageindex==1);
			int totalnumber = this.totalRecords(cid);
			int totalpage = totalnumber % pagesize ==0 ? totalnumber / pagesize : totalnumber /pagesize +1;
			pageinfo.setIslastpage(totalpage==pageindex);
			pageinfo.setPageindex(pageindex);
			pageinfo.setPagesize(pagesize);
			pageinfo.setTotalnumber(totalnumber);
			pageinfo.setTotalpage(totalpage);
			
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
		return pageinfo;
	}
	
	@Override
	public PageInfo<Goods> listgoods(int pagesize, int pageindex) {
		
		PageInfo<Goods> pageinfo = new PageInfo<Goods>();
		List<Goods> list = new ArrayList<Goods>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			//1获取连接
			conn = DBUtil.getConnection();
			//2通过连接创建PreparedStatement对象;
			String sql="select t2.* from (select t1.* ,rownum rn from (select * from t_goods) t1 where rownum<=? ) t2 where rn>=?";
			ps = conn.prepareStatement(sql);
			int endindex = pagesize * pageindex;
			int startindex = (pageindex-1)*pagesize +1;
			ps.setInt(1, endindex);
			ps.setInt(2, startindex);
			//3执行操作
			rs = ps.executeQuery();
			//4取数据;
			Goods goods = null;
			org.lanqiao.dao.PublisherDao dao = new org.lanqiao.dao.impl.PublisherDaoImpl();
			while(rs.next()){
				goods = new Goods(rs.getString("gid"), rs.getString("gtitle"), rs.getString("gauthor"), rs.getDouble("gsaleprice"), rs.getDouble("ginprice"), rs.getString("gdesc"), rs.getString("gimg"), rs.getInt("gclicks"), rs.getString("cid"), rs.getString("pid"));
				//处理外键
				Publisher publisher = dao.get(goods.getPid());
				goods.setPublisher(publisher);
				list.add(goods);
			}
			//给pageinfo对象赋值;
			pageinfo.setData(list);
			pageinfo.setIsfirstpage(pageindex==1);
			int totalnumber = this.totalGoodsRecords();
			int totalpage = totalnumber % pagesize ==0 ? totalnumber / pagesize : totalnumber /pagesize +1;
			pageinfo.setIslastpage(totalpage==pageindex);
			pageinfo.setPageindex(pageindex);
			pageinfo.setPagesize(pagesize);
			pageinfo.setTotalnumber(totalnumber);
			pageinfo.setTotalpage(totalpage);
			
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
		return pageinfo;
	}
	
	private int totalGoodsRecords() {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int total = 0;
		try{
			//1获取连接
			conn = DBUtil.getConnection();
			//2通过连接创建PreparedStatement对象;
			String sql="select count(*) from t_goods";
			ps = conn.prepareStatement(sql);
			//3执行操作
			rs = ps.executeQuery();
			//4取数据;
			if(rs.next()){
				total = rs.getInt(1);
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
		return total;
	
	}

	//获取某一类别商口总数;
	@Override
	public int totalRecords(String cid) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int total = 0;
		try{
			//1获取连接
			conn = DBUtil.getConnection();
			//2通过连接创建PreparedStatement对象;
			String sql="select count(*) from t_goods where cid=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, cid);
			//3执行操作
			rs = ps.executeQuery();
			//4取数据;
			if(rs.next()){
				total = rs.getInt(1);
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
		return total;
	}
	@Override
	public Goods get(String gid) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Goods goods = null;
		try{
			//1获取连接
			conn = DBUtil.getConnection();
			//2通过连接创建PreparedStatement对象;
			String sql="select * from t_goods where gid=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, gid);
			//3执行操作
			rs = ps.executeQuery();
			//4取数据;
			if(rs.next()){
				goods = new Goods(rs.getString("gid"), rs.getString("gtitle"), rs.getString("gauthor"), rs.getDouble("gsaleprice"), rs.getDouble("ginprice"), rs.getString("gdesc"), rs.getString("gimg"), rs.getInt("gclicks"), rs.getString("cid"), rs.getString("pid"));
			}
			//处理外键;
			org.lanqiao.dao.PublisherDao dao = new org.lanqiao.dao.impl.PublisherDaoImpl();
			Publisher publisher = dao.get(goods.getPid());
			goods.setPublisher(publisher);
		}catch(Exception e){}
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
		return goods;
	}

	@Override
	public List<Goods> getMaxClicks() {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Goods good = null;
		List<Goods> goods = new ArrayList<Goods>();
		try{
			//1获取连接
			conn = DBUtil.getConnection();
			//2通过连接创建PreparedStatement对象;
			String sql="select t1.*, rownum from (select * from t_goods order by gclicks desc) t1 where rownum<3";
			ps = conn.prepareStatement(sql);
			//3执行操作
			rs = ps.executeQuery();
			//4取数据;
			while(rs.next()){
				good = new Goods(rs.getString("gid"), rs.getString("gtitle"), rs.getString("gauthor"), rs.getDouble("gsaleprice"), rs.getDouble("ginprice"), rs.getString("gdesc"), rs.getString("gimg"), rs.getInt("gclicks"), rs.getString("cid"), rs.getString("pid"));
				goods.add(good);
			}
		}catch(Exception e){}
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
		return goods;
	}

	@Override
	public void addClickNumber(String gid) {
		// TODO Auto-generated method stub
				Connection conn = null;
				PreparedStatement ps = null;
				try{
					//1获取连接
					conn = DBUtil.getConnection();
					//2通过连接创建PreparedStatement对象;
					String sql="update t_goods set gclicks=(gclicks+1) where gid=?";
					ps = conn.prepareStatement(sql);
					//3执行操作
					ps.setString(1, gid);
					//向数据库中更新商品点击量
					ps.executeUpdate();
				}catch(Exception e){
					
				}
				finally{
					//5关闭对象
					try {
						if(ps!=null) ps.close();
						if(conn!=null) conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		
	}

}
