package org.lanqiao.entity;

public class Goods {
	private String gid;
	private String gtitle;
	private String gauthor;
	private double gsaleprice;
	private double ginprice;
	private String gdesc;
	private String gimg; //图片 --数据库中存放图片名称即名;
	private int gclicks;
	private String cid; //外键;
	private String pid; //外键
	private Publisher publisher; //外键做成一个对象
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public String getGtitle() {
		return gtitle;
	}
	public void setGtitle(String gtitle) {
		this.gtitle = gtitle;
	}
	public String getGauthor() {
		return gauthor;
	}
	public void setGauthor(String gauthor) {
		this.gauthor = gauthor;
	}
	public double getGsaleprice() {
		return gsaleprice;
	}
	public void setGsaleprice(double gsaleprice) {
		this.gsaleprice = gsaleprice;
	}
	public double getGinprice() {
		return ginprice;
	}
	public void setGinprice(double ginprice) {
		this.ginprice = ginprice;
	}
	public String getGdesc() {
		return gdesc;
	}
	public void setGdesc(String gdesc) {
		this.gdesc = gdesc;
	}
	public String getGimg() {
		return gimg;
	}
	public void setGimg(String gimg) {
		this.gimg = gimg;
	}
	public int getGclicks() {
		return gclicks;
	}
	public void setGclicks(int gclicks) {
		this.gclicks = gclicks;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	
	
	public Goods(String gid, String gtitle, String gauthor, double gsaleprice,
			double ginprice, String gdesc, String gimg, int gclicks,
			String cid, String pid) {
		super();
		this.gid = gid;
		this.gtitle = gtitle;
		this.gauthor = gauthor;
		this.gsaleprice = gsaleprice;
		this.ginprice = ginprice;
		this.gdesc = gdesc;
		this.gimg = gimg;
		this.gclicks = gclicks;
		this.cid = cid;
		this.pid = pid;
	}
	public Goods() {
		// TODO Auto-generated constructor stub
	}
	public Publisher getPublisher() {
		return publisher;
	}
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	
}
