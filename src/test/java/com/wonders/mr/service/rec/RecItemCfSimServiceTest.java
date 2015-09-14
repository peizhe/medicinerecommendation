package com.wonders.mr.service.rec;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wonders.bud.framework.common.AbstractTestCase;
import com.wonders.mr.service.item.modal.po.ItemPO;
import com.wonders.mr.service.recitemcfsim.service.RecItemCfSimService;

public class RecItemCfSimServiceTest extends AbstractTestCase {

	@Autowired
	private RecItemCfSimService recItemCfSimService;
	
	@Test
	public void findByItemIdTest(){
		List<ItemPO> result = recItemCfSimService.findByItemId(1);
		Assert.assertEquals(1, result.size());
	}
	
}
