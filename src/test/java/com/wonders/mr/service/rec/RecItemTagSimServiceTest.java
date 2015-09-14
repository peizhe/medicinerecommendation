package com.wonders.mr.service.rec;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wonders.bud.framework.common.AbstractTestCase;
import com.wonders.mr.service.item.modal.po.ItemPO;
import com.wonders.mr.service.recitemtagsim.service.RecItemTagSimService;


public class RecItemTagSimServiceTest extends AbstractTestCase {
	
	@Autowired
	private RecItemTagSimService recItemTagSimService;
	
	@Test
	public void findByItemIdTest(){
		List<ItemPO> result = recItemTagSimService.findByItemId(1);
		Assert.assertEquals(1, result.size());
		
	}

}
