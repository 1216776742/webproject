package org.lanqiao.test;

import org.junit.Test;
import org.lanqiao.entity.Goods;
import org.lanqiao.entity.PageInfo;

public class GoodsTest {
	@Test
	public void testgoods(){
		org.lanqiao.dao.GoodsDao dao =new org.lanqiao.dao.impl.GoodsDaoImpl();
		PageInfo<Goods> pageinfo = dao.list("1", 10, 1);
		System.out.println(pageinfo.getData());
	}
}
