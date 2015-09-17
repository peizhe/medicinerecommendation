package com.wonders.mr.service.item.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wonders.bud.framework.common.util.QueryParam;
import com.wonders.mr.service.item.dao.ItemDao;
import com.wonders.mr.service.item.dao.TagItemDao;
import com.wonders.mr.service.item.modal.po.ItemPO;
import com.wonders.mr.service.item.modal.po.TagItemPO;
import com.wonders.mr.service.item.service.TagItemService;

@Service("tagItemServiceImpl")
public class TagItemServiceImpl implements TagItemService {

	@Autowired
	TagItemDao tagItemDao;
	@Autowired
	ItemDao itemDao;

	@Override
	public List<ItemPO> findById(long id) {
		
		QueryParam param = new QueryParam();
		
		Map<String, Object> eq = new HashMap<>();
		eq.put("tagId", id);
		param.setEq(eq);
		TagItemPO tagItemPO = tagItemDao.findByAnd(param).get(0);
		
		String itemIdList = tagItemPO.getItemIdList();
		String[] ids = itemIdList.split(",");
		List<ItemPO> itemList = new ArrayList<ItemPO>();
		for (String item : ids) {
			long itemId = Long.parseLong(item);
			ItemPO itemPO = itemDao.get(itemId);
			itemList.add(itemPO);
		}
		return itemList;
	}
}
