package com.wonders.mr.service.action;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wonders.bud.framework.common.AbstractTestCase;
import com.wonders.mr.service.action.service.ActionService;
import com.wonders.mr.service.item.modal.po.ItemPO;
/**
 * 用户购买行为测试类
 * @author xh
 *
 */
public class ActionServiceTest  extends AbstractTestCase{

	@Autowired
	private ActionService actionService;
	
	
	@Test
	public void findByUserIdTest() {
		List<ItemPO> result = actionService.findByUserId(1);
		Assert.assertEquals(53, result.size());
	}
}
