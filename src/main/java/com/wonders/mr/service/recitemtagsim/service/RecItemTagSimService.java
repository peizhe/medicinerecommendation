package com.wonders.mr.service.recitemtagsim.service;

import java.util.List;

import com.wonders.mr.service.item.modal.po.ItemPO;

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
	public List<ItemPO> findByItemId(long id);

}
