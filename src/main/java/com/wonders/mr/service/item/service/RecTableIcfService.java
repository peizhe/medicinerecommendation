package com.wonders.mr.service.item.service;

import java.util.List;
/**
 * 
 * author rk
 */
import com.wonders.mr.service.item.modal.po.ItemPO;

public interface RecTableIcfService {
	/**
	 * 根据用户id（用药历史）查询相似用户
	 * @param userId
	 * @return
	 */
	public List<ItemPO> findByUserId(long userId);
}
