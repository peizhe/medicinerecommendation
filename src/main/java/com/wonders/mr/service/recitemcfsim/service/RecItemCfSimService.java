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
	 * 根据根据用过该药用户用药id查找还用过什么药
	 * 
	 * @param id
	 * @return
	 */
	public List<ItemPO> findByItemId(long id);
}
