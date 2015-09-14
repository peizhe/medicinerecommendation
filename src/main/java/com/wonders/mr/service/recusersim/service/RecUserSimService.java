package com.wonders.mr.service.recusersim.service;

import com.wonders.mr.service.recusersim.modal.RecUserSimPO;

/**
 * 找病友service接口
 * 
 * @author lixiang
 *
 */
public interface RecUserSimService {
	
	/**
	 * 根据用户id查找用户
	 * 
	 * @param id
	 * @return
	 */
	public RecUserSimPO findByUserId(Long id);

}
