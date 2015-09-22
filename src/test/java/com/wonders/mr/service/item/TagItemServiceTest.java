package com.wonders.mr.service.item;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wonders.bud.framework.common.AbstractTestCase;
import com.wonders.mr.service.item.modal.po.ItemPO;
import com.wonders.mr.service.item.service.TagItemService;

public class TagItemServiceTest extends AbstractTestCase{

	@Autowired
	private TagItemService tagItemService;
	
	@Test
	public void findByIdTest() {
		//List<ItemPO> result = tagItemService.findById(1);
		//Assert.assertEquals(3, result.size());
	}
}
