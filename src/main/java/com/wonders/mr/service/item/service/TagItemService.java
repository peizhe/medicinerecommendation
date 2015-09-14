package com.wonders.mr.service.item.service;

import java.util.List;


import com.wonders.mr.service.item.modal.po.ItemPO;

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
	public List<ItemPO> findById(long id);
	
	
}
