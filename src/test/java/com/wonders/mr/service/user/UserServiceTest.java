package com.wonders.mr.service.user;


import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wonders.bud.framework.common.AbstractTestCase;
import com.wonders.mr.service.user.modal.UserPO;
import com.wonders.mr.service.user.service.UserService;
import com.wonders.mr.util.Constant;

public class UserServiceTest extends AbstractTestCase{

	private String abc = "qwertyuioplkjhgfdsazxcvbnm";
	
	@Autowired
	private UserService userService;
	
	@Test
	public void readTest() {
		Assert.assertNotNull(userService);
	}
	
	@Test
	public void saveTest() {
		
		UserPO userPO = new UserPO();
		userPO.setAge(25);
		userPO.setDeleteFlag(Constant.USER_NOT_DELETE);
		userPO.setGender("男");
		userPO.setLoginName("user1");
		userPO.setName("user1");
		
	}
	
	@Test
	public void findAllTest() {
		
		List<UserPO> userpos = userService.findAll();
		
		for(UserPO po : userpos) {
			String name = genName();
			po.setName(name);
			System.out.println("设置姓名:" + name);
			userService.update(po);
		}
	}
	
	/**
	 * 产生0到n的随机数
	 * @param n
	 * @return
	 */
	public int genRandom(int n) {
		int random = (int)(Math.random() * n);
		return random;
	}
	
	/**
	 * 生成大于4且小于7的随机数
	 * @return
	 */
	public int genNameLen() {
		int result = 0;
		while (true) {
			int random = genRandom(7);
			if(random > 4) {
				result = random;
				break;
			}
		}
		return result;
	}
	
	/**
	 * 随机从26个字母中取出4到7个字母组成名字
	 * @return
	 */
	public String genName() {
		StringBuffer sb = new StringBuffer();
		int nameLen = genNameLen();
		for(int i = 0; i < nameLen; i++) {
			int target = genRandom(26);
			sb.append(abc.charAt(target));
		}
		return sb.toString();
	}
	
}
