package com.wonders.mr.service.recitemtagsim.service;

import com.wonders.mr.service.recitemtagsim.modal.RecItemTagSimPO;

/**
 * 物品相似度service接口
 * 
 * @author lixiang
 *
 */
public interface RecItemTagSimService {
	
	/**
	 * 根据ID查找对象
	 * 
	 * @param id
	 * @return
	 */
	public RecItemTagSimPO findByItemId(Long id);

}
