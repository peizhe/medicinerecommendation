package com.wonders.mr.service.action.service;

import java.util.List;

import com.wonders.mr.service.item.modal.po.ItemPO;

public interface ActionService {

	/**
	 * 根据用户id，查询用户购买了哪些药
	 * @return
	 */
	public List<ItemPO> findByUserId(long userId);
}
