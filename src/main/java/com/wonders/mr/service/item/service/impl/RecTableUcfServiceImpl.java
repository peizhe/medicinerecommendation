package com.wonders.mr.service.item.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		RecTableUcfPO recTableUcf = recTableUcfDao.get(userId);
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
