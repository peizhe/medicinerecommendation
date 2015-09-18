package com.wonders.mr.service.recitemtagsim.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wonders.bud.framework.common.util.QueryParam;
import com.wonders.mr.service.item.modal.po.ItemPO;
import com.wonders.mr.service.item.service.ItemService;
import com.wonders.mr.service.recitemtagsim.dao.RecItemTagSimDao;
import com.wonders.mr.service.recitemtagsim.modal.RecItemTagSimPO;
import com.wonders.mr.service.recitemtagsim.service.RecItemTagSimService;

/**
 * 类似药品展示模块service实现
 * 
 * @author lixiang
 *
 */
@Service("recItemTagSimServiceImpl")
public class RecItemTagSimServiceImpl implements RecItemTagSimService {

	@Autowired
	RecItemTagSimDao recItemTagSimDao;
	@Autowired
	ItemService itemService;
	
	@Override
	public List<ItemPO> findByItemId(long id) {
		
		QueryParam param = new QueryParam();
		Map<String, Object> eq = new HashMap<String, Object>();
		eq.put("itemId", id);
		param.setEq(eq);
		RecItemTagSimPO recItemTagSim = recItemTagSimDao.findByAnd(param).get(0);
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
