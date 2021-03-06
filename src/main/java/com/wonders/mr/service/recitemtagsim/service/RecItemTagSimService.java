package com.wonders.mr.service.recitemtagsim.service;

import java.util.List;

import com.wonders.mr.service.item.modal.po.ItemPO;

/**
 * 类似药品展示模块service接口
 * 
 * @author lixiang
 *
 */
public interface RecItemTagSimService {
	
	/**
	 * 根据药品ID查找相似药品
	 * 
	 * @param id
	 * @return
	 */
	public List<ItemPO> findByItemId(long id);

}
