package com.wonders.mr.service.item.service;

import java.util.List;

import com.wonders.bud.framework.common.page.Page;
import com.wonders.mr.service.item.modal.po.ItemPO;
import com.wonders.mr.service.item.modal.po.TagItemPO;
import com.wonders.mr.service.item.modal.po.TagPO;

/**
 * 
 * @author xh
 *
 */
public interface TagItemService {
	
	/**
	 * 根据tagId，查找对应的药品
	 * @param id tagid
	 * @return
	 */
	public Page<ItemPO> findById(long id,int start, int size);

	/**
	 * 根据tagId，分页查找对应的药品
	 * @param id tagid
	 * @return
	 */
	public Page<ItemPO> findItems(String[] ids,int start, int size);
	
	
}
