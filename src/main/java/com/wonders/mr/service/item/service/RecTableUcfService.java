package com.wonders.mr.service.item.service;

import java.util.List;

import com.wonders.mr.service.item.modal.po.ItemPO;

/**
 * 
 * @author xh
 *
 */
public interface RecTableUcfService {

	/**
	 * 根据userId查找为该用户推荐的药品
	 * @param userId
	 * @return
	 */
	public List<ItemPO> findByUserId(long userId);
}
