package com.wonders.mr.service.user;


import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wonders.bud.framework.common.AbstractTestCase;
import com.wonders.mr.service.user.dao.UserDao;
import com.wonders.mr.service.user.modal.UserPO;
import com.wonders.mr.util.Constant;

public class UserServiceTest extends AbstractTestCase{

	@Autowired
	private UserDao userDao;
	
	@Test
	public void readTest() {
		Assert.assertNotNull(userDao);
	}
	
	@Test
	public void saveTest() {
		
		UserPO userPO = new UserPO();
		userPO.setAge(25);
		userPO.setDeleteFlag(Constant.USER_NOT_DELETE);
		userPO.setGender("男");
		userPO.setLoginName("user1");
		userPO.setName("user1");
		
		userDao.save(userPO);
	}
}
