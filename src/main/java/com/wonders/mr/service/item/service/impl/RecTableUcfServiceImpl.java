package com.wonders.mr.service.item.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.UncategorizedDataAccessException;
import org.springframework.stereotype.Service;

import com.wonders.bud.framework.common.util.QueryParam;
import com.wonders.mr.service.item.dao.RecTableUcfDao;
import com.wonders.mr.service.item.modal.po.ItemPO;
import com.wonders.mr.service.item.modal.po.RecTableUcfPO;
import com.wonders.mr.service.item.service.ItemService;
import com.wonders.mr.service.item.service.RecTableUcfService;

@Service("recTableUcfServiceImpl")
public class RecTableUcfServiceImpl implements RecTableUcfService{

	@Autowired
	private RecTableUcfDao recTableUcfDao;
	@Autowired
	ItemService itemService;
	
	@Override
	public List<ItemPO> findByUserId(long userId) {
		
		QueryParam qParam = new QueryParam();
		Map<String, Object> eq = new HashMap<String, Object>();
		eq.put("userId", userId);
		qParam.setEq(eq);
		RecTableUcfPO recTableUcf = recTableUcfDao.findByAnd(qParam).get(0);
		String itemIdList = recTableUcf.getItemIdList();
		String[] ids = itemIdList.split(",");
		List<ItemPO> itemList = new ArrayList<ItemPO>();
		for(String id : ids) {
			long itemId = Long.parseLong(id);
			ItemPO item = itemService.findById(itemId);
			itemList.add(item);
		}
		return itemList;
	}
}
