package org.lanqiao.dao;

import java.util.List;

import org.lanqiao.entity.Goods;
import org.lanqiao.entity.PageInfo;

public interface GoodsDao {
	public PageInfo<Goods> list(String cid,int pagesize ,int pageindex);
	public int totalRecords(String cid);
	public Goods get(String gid);
	PageInfo<Goods> listgoods(int pagesize, int pageindex);
	public List<Goods> getMaxClicks();
	public void addClickNumber(String gid);
}
