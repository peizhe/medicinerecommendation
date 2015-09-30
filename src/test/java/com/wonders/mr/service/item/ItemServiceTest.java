package com.wonders.mr.service.item;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wonders.bud.framework.common.AbstractTestCase;
import com.wonders.mr.service.item.modal.po.ItemPO;
import com.wonders.mr.service.item.service.ItemService;

public class ItemServiceTest extends AbstractTestCase{

	@Autowired
	private ItemService itemService;
	
	@Test
	public void findByIdTest() {
		
		ItemPO item = itemService.findById(1);
		Assert.assertEquals(new Long(1), item.getItemId());
	}
	
	@Test
	public void findByInputTest() {
		List<ItemPO> result = itemService.findByInput("微生");
		System.out.println(result.size());
		
	}
	
	@Test
	public void findByTest() {
		
	}
	
}
