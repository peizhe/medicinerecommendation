package com.wonders.mr.service.item;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wonders.bud.framework.common.AbstractTestCase;
import com.wonders.mr.service.item.modal.po.ItemPO;
import com.wonders.mr.service.item.service.RecTableIcfService;

public class RecTableIcfServiceTest extends AbstractTestCase{
	@Autowired
	private RecTableIcfService recTableUcfService;
	
	@Test
	public void findByUserIdTest(){
		List<ItemPO> list = recTableUcfService.findByUserId(1);
		Assert.assertEquals(10, list.size());
	}
}
