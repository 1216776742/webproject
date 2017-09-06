package org.lanqiao.service;

import java.util.List;

import org.lanqiao.entity.Goods;
import org.lanqiao.entity.PageInfo;

public interface GoodsService {
	public PageInfo<Goods> goodsList(String cid,int pagesize,int pageindex);
	public Goods getGoodsBygid(String gid);
	public PageInfo<Goods> goodsList(int pagesize, int pageindex);
	public List<Goods> getMaxClicks();
	public void addClickNumber(String gid);
}
