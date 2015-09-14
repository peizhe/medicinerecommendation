package com.wonders.mr.service.rec;


import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wonders.bud.framework.common.AbstractTestCase;
import com.wonders.mr.service.recusersim.service.RecUserSimService;
import com.wonders.mr.service.user.modal.UserPO;


public class RecUserSimServiceTest extends AbstractTestCase {

	@Autowired
	private RecUserSimService recUserSimService;
	
	@Test
	public void findByUserIdTest() {
		List<UserPO> result = recUserSimService.findByUserId(1);
		Assert.assertEquals(2, result.size());
		
	}
	
	
}
