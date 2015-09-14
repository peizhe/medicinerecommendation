package com.wonders.mr.service.recitemcfsim.service;

import java.util.List;

import com.wonders.mr.service.item.modal.po.ItemPO;

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
	public List<ItemPO> findByItemId(long id);
}
