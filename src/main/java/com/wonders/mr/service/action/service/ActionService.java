package com.wonders.mr.service.action.service;

import java.util.List;

import com.wonders.bud.framework.common.page.Page;
import com.wonders.mr.service.action.modal.ActionPO;
import com.wonders.mr.service.item.modal.po.ItemPO;

public interface ActionService {

	/**
	 * 根据用户id，查询用户购买了哪些药
	 * @return
	 */
	public List<ItemPO> findByUserId(long userId);
	
	public ActionPO saveAction(ActionPO actionpo);
	
	/*分页获取用户购买了哪些药品*/
	public Page<ActionPO> findByPage(Page<ActionPO> page);
	public Page<ActionPO> findItems(long userId,int start,int size);
}
