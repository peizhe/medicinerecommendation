package com.wonders.mr.service.item;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.wonders.bud.framework.common.AbstractTestCase;
import com.wonders.mr.service.action.modal.ActionPO;
import com.wonders.mr.service.action.service.ActionService;


public class ActionTest extends AbstractTestCase{

	@Autowired
	private ActionService actionservice;
	
	
	@Test
	public void findByUserIdTest(){
		ActionPO actionpo = new ActionPO();
		actionpo.setUserId((long) 1);
		actionpo.setItemId((long) 11);
		actionservice.saveAction(actionpo);
	}
	
}
