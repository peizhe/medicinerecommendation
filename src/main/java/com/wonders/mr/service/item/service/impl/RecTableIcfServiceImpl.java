package com.wonders.mr.service.item.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wonders.bud.framework.common.util.QueryParam;
import com.wonders.mr.service.item.dao.RecTableIcfDao;
import com.wonders.mr.service.item.modal.po.ItemPO;
import com.wonders.mr.service.item.modal.po.RecTableIcfPO;
import com.wonders.mr.service.item.service.ItemService;
import com.wonders.mr.service.item.service.RecTableIcfService;

@Service("recTableIcfServiceImpl")
public class RecTableIcfServiceImpl implements RecTableIcfService{
	
	@Resource
	private RecTableIcfDao rectableicfdao;
	@Resource
	ItemService itemService;
	
	@Override
	public List<ItemPO> findByUserId(long userId) {
		// TODO Auto-generated method stub
		
		QueryParam qParam = new QueryParam();
		Map<String, Object> eq = new HashMap<String, Object>();
		eq.put("userId", userId);
		qParam.setEq(eq);
		RecTableIcfPO recTableIcf = rectableicfdao.findByAnd(qParam).get(0);
		String itemIdList = recTableIcf.getItemIdList();
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
