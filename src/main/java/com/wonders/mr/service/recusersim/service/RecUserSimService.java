package com.wonders.mr.service.recusersim.service;

import java.util.List;

import com.wonders.mr.service.user.modal.UserPO;

/**
 * 找病友service接口
 * 
 * @author lixiang
 *
 */
public interface RecUserSimService {
	
	/**
	 * 根据用户id查找用药相似用户
	 * 
	 * @param id
	 * @return
	 */
	public List<UserPO> findByUserId(long id);

}
