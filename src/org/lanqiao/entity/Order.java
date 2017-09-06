package org.lanqiao.entity;

import java.util.Date;

public class Order {
	private String orderid;
	private String userid;
	private double totalprice;
	private Date orderdate;
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public double getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}
	public Date getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}
	public Order(String orderid, String userid, double totalprice,
			Date orderdate) {
		super();
		this.orderid = orderid;
		this.userid = userid;
		this.totalprice = totalprice;
		this.orderdate = orderdate;
	}
	public Order() {
		super();
	}
	public Order(String orderid2, String uesrid, double totalprice2) {
		// TODO Auto-generated constructor stub
		super();
		this.orderid = orderid2;
		this.userid = uesrid;
		this.totalprice = totalprice2;
	}
	
	
}
	
