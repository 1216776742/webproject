package org.lanqiao.test;

import org.junit.Test;
import org.lanqiao.util.MailUtil;

public class NewsDaoTest {
	@Test
	public void testDao(){
		MailUtil.sendMail("2634325131@qq.com", "旷课很严重......", "罚款10元,30分钟不来，再追加10元,依此类推 ......");
	}
}
