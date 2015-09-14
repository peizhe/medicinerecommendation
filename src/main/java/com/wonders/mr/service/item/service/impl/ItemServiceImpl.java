package com.wonders.mr.service.item.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wonders.bud.framework.common.util.QueryParam;
import com.wonders.mr.service.item.dao.ItemDao;
import com.wonders.mr.service.item.modal.po.ItemPO;
import com.wonders.mr.service.item.service.ItemService;

@Service("itemServiceImpl")
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	ItemDao itemDao;
	
	@Override
	public ItemPO findById(long id) {
		return itemDao.get(id); 
	}

	@Override
	public List<ItemPO> findByInput(String input) {
		Map<String, Object> like = new HashMap<String, Object>();
		like.put("itemName", input);
		like.put("category", input);
		like.put("symptomDesc", input);
		like.put("company", input);
		like.put("component",input);
		
		QueryParam param = new QueryParam();
		param.setLike(like);
		
		List<ItemPO> list =itemDao.findByOr(param);
		return list;
	}
}
