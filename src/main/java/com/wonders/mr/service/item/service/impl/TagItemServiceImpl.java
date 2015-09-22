package com.wonders.mr.service.item.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wonders.bud.framework.common.page.Page;
import com.wonders.bud.framework.common.util.QueryParam;
import com.wonders.mr.service.item.dao.ItemDao;
import com.wonders.mr.service.item.dao.TagDao;
import com.wonders.mr.service.item.dao.TagItemDao;
import com.wonders.mr.service.item.modal.po.ItemPO;
import com.wonders.mr.service.item.modal.po.TagItemPO;
import com.wonders.mr.service.item.modal.po.TagPO;
import com.wonders.mr.service.item.service.TagItemService;

@Service("tagItemServiceImpl")
public class TagItemServiceImpl implements TagItemService {

	@Autowired
	TagItemDao tagItemDao;
	@Autowired
	ItemDao itemDao;
	@Autowired
	private TagDao tagdao;

	@Override
	public Page<ItemPO> findById(long id,int start, int size) {
		
		QueryParam param = new QueryParam();
		
		Map<String, Object> eq = new HashMap<>();
		eq.put("tagId", id);
		param.setEq(eq);
		TagItemPO tagItemPO = tagItemDao.findByAnd(param).get(0);
		
		String itemIdList = tagItemPO.getItemIdList();
		String[] ids = itemIdList.split(",");
		Page<ItemPO> itemList = findItems(ids,start,size);
		return itemList;
	}
	@Override
	public Page<ItemPO> findItems(String[] ids,int start, int size){
		
		Page<ItemPO> page=new Page<>();
		QueryParam query=new QueryParam();
		Map<String, Object[]> in=new HashMap<>();
		Object[] itemIds=new Object[ids.length];
		for (int i=0;i<itemIds.length;i++) {
			itemIds[i]=Long.parseLong(ids[i]);
		}
		in.put("itemId", itemIds);
		query.setIn(in);
		page.setStart(start);
		page.setPagesize(size);
		page.setParam(query);
		page=itemDao.findByPage(page);
		return page;
	}
}
