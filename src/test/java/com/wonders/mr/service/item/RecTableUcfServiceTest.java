package com.wonders.mr.service.item;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wonders.bud.framework.common.AbstractTestCase;
import com.wonders.mr.service.item.modal.po.ItemPO;
import com.wonders.mr.service.item.service.RecTableUcfService;

public class RecTableUcfServiceTest extends AbstractTestCase{

	@Autowired
	private RecTableUcfService recTableUcfService;
	
	@Test
	public void findByUserIdTest(){
		
		List<ItemPO> list = recTableUcfService.findByUserId(1);
		
		Assert.assertEquals(4, list.size());
	}
	
}
