package org.lanqiao.service.impl;

import java.util.List;

import org.lanqiao.entity.Goods;
import org.lanqiao.entity.PageInfo;
import org.lanqiao.service.GoodsService;

public class GoodsServiceImpl implements GoodsService {
	private org.lanqiao.dao.GoodsDao dao = null;
	public GoodsServiceImpl() {
		dao = new org.lanqiao.dao.impl.GoodsDaoImpl();
	}
	@Override
	public PageInfo<Goods> goodsList(String cid, int pagesize, int pageindex) {
		return dao.list(cid, pagesize, pageindex);
	}
	@Override
	public PageInfo<Goods> goodsList(int pagesize, int pageindex) {
		return dao.listgoods(pagesize, pageindex);
	}
	@Override
	public Goods getGoodsBygid(String gid) {
		// TODO Auto-generated method stub
		return dao.get(gid);
	}
	@Override
	public List<Goods> getMaxClicks() {
		// TODO Auto-generated method stub
		return dao.getMaxClicks();
	}
	@Override
	public void addClickNumber(String gid) {
		// TODO Auto-generated method stub
		 dao.addClickNumber(gid);
	}

}
