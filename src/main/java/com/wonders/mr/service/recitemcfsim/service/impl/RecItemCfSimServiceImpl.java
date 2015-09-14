package com.wonders.mr.service.recitemcfsim.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wonders.mr.service.item.modal.po.ItemPO;
import com.wonders.mr.service.item.service.ItemService;
import com.wonders.mr.service.recitemcfsim.dao.RecItemCfSimDao;
import com.wonders.mr.service.recitemcfsim.modal.RecItemCfSimPO;
import com.wonders.mr.service.recitemcfsim.service.RecItemCfSimService;

/**
 * 用过该药的用户还用过service实现
 * 
 * @author lixiang
 *
 */
@Service("recItemCfSimServiceImpl")
public class RecItemCfSimServiceImpl implements RecItemCfSimService {

	@Autowired
	RecItemCfSimDao recItemCfSimDao;
	
	@Autowired
	ItemService itemService;
	
	@Override
	public List<ItemPO> findByItemId(long id) {
		RecItemCfSimPO recItemCfSim = recItemCfSimDao.get(id);
		String itemIdList = recItemCfSim.getItemIdList();
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
