package com.wonders.mr.service.recitemcfsim.service;

import com.wonders.mr.service.recitemcfsim.modal.RecItemCfSimPO;

/**
 * 用药service接口定义
 * 
 * @author lixiang
 *
 */
public interface RecItemCfSimService {

	/**
	 * 根据药品id查找
	 * 
	 * @param id
	 * @return
	 */
	public RecItemCfSimPO findByItemId(Long id);
}
