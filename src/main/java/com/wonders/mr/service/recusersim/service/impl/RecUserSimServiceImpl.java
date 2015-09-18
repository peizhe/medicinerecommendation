package com.wonders.mr.service.recusersim.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wonders.bud.framework.common.util.QueryParam;
import com.wonders.mr.service.recusersim.dao.RecUserSimDao;
import com.wonders.mr.service.recusersim.modal.RecUserSimPO;
import com.wonders.mr.service.recusersim.service.RecUserSimService;
import com.wonders.mr.service.user.modal.UserPO;
import com.wonders.mr.service.user.service.UserService;

/**
 * 找病友（相似用户推荐）service实现
 * 根据用户id查找用药相似用户
 * 
 * @author lixiang
 *
 */
@Service("recUserSimServiceImpl")
public class RecUserSimServiceImpl implements RecUserSimService {

	@Autowired
	RecUserSimDao recUserSimDao;
	@Autowired
	UserService userService;
	
	@Override
	public List<UserPO> findByUserId(long id) {
		
		
		QueryParam param = new QueryParam();
		Map<String, Object> eq = new HashMap<String, Object>();
		eq.put("userId", id);
		param.setEq(eq);
		
		RecUserSimPO recUserSimPO = recUserSimDao.findByAnd(param).get(0);
		
		String userIdList = recUserSimPO.getUserIdList();
		String[] ids = userIdList.split(",");
		List<UserPO> userList = new ArrayList<UserPO >();
		for(String item: ids) {
			long idL = Long.parseLong(item);
			UserPO userPO = userService.findById(idL);
			userList.add(userPO);
		}
		return userList;
	}

}
