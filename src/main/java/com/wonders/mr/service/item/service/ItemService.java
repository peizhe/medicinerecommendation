package com.wonders.mr.service.item.service;

import java.util.List;

import com.wonders.mr.service.item.modal.po.ItemPO;

/**
 * 药品服务层接口定义
 * @author xh
 *
 */
public interface ItemService {

	/**
	 * 根据药品id查找药品
	 * @param id 药品id
	 * @return 
	 */
	public ItemPO findById(long id);
	
	/**
	 * 根据用户输入的检索内容，模糊搜索药品表所有字段
	 * @param input
	 * @return
	 */
	public List<ItemPO> findByInput(String input);
	
}
