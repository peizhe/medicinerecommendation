package com.wonders.mr.service.recitemtagsim.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wonders.mr.service.item.modal.po.ItemPO;
import com.wonders.mr.service.item.service.ItemService;
import com.wonders.mr.service.recitemtagsim.dao.RecItemTagSimDao;
import com.wonders.mr.service.recitemtagsim.modal.RecItemTagSimPO;
import com.wonders.mr.service.recitemtagsim.service.RecItemTagSimService;

/**
 * 物品相似度service实现
 * 
 * @author lixiang
 *
 */
@Service("RecItemTagSimServiceImpl")
public class RecItemTagSimServiceImpl implements RecItemTagSimService {

	@Autowired
	RecItemTagSimDao recItemTagSimDao;
	@Autowired
	ItemService itemService;
	
	@Override
	public List<ItemPO> findByItemId(long id) {
		RecItemTagSimPO recItemTagSim = recItemTagSimDao.get(id);
		String itemIdList = recItemTagSim.getItemIdList();
		String[] ids = itemIdList.split(",");
		List<ItemPO> itemList = new ArrayList<ItemPO>();
		for(String item : ids) {
			long idL = Long.parseLong(item);
			ItemPO itemPO =itemService.findById(idL);
			itemList.add(itemPO);
		}
		return itemList;

	}

}
