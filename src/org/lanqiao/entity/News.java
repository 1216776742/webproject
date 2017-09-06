package org.lanqiao.entity;

import java.util.Date;

public class News {
	private String tid;
	private String title;
	private String tcontent;
	private java.util.Date tpubdate;
	public News() {
		// TODO Auto-generated constructor stub
	}
	public News(String tid, String title, String tcontent, Date tpubdate) {
		this.tid = tid;
		this.title = title;
		this.tcontent = tcontent;
		this.tpubdate = tpubdate;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTcontent() {
		return tcontent;
	}
	public void setTcontent(String tcontent) {
		this.tcontent = tcontent;
	}
	public java.util.Date getTpubdate() {
		return tpubdate;
	}
	public void setTpubdate(java.util.Date tpubdate) {
		this.tpubdate = tpubdate;
	}
	
	
	
}
