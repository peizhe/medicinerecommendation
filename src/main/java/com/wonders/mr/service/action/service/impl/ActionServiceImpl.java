package com.wonders.mr.service.action.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wonders.bud.framework.common.util.QueryParam;
import com.wonders.mr.service.action.dao.ActionDao;
import com.wonders.mr.service.action.modal.ActionPO;
import com.wonders.mr.service.action.service.ActionService;
import com.wonders.mr.service.item.modal.po.ItemPO;
import com.wonders.mr.service.item.service.ItemService;

@Service("actionServiceImpl")
public class ActionServiceImpl implements ActionService {

	@Autowired
	private ActionDao actionDao;
	@Autowired
	private ItemService itemService;

	@Override
	public List<ItemPO> findByUserId(long userId) {

		QueryParam param = new QueryParam();

		Map<String, Object> eq = new HashMap<String, Object>();
		eq.put("userId", userId);
		param.setEq(eq);
		List<ItemPO> items = new ArrayList<>();
		List<ActionPO> actionList = actionDao.findByAnd(param);
		for (ActionPO actionPO : actionList) {// 遍历，查找所有药品

			ItemPO itemPO = itemService.findById(actionPO.getItemId());
			items.add(itemPO);
		}
		return items;

	}

	@Override
	public ActionPO saveAction(ActionPO actionpo) {
		// TODO Auto-generated method stub
		return actionDao.save(actionpo);
	}

}
