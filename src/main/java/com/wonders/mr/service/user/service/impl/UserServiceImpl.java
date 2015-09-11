package com.wonders.mr.service.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.mr.service.user.dao.UserDao;
import com.wonders.mr.service.user.modal.UserPO;
import com.wonders.mr.service.user.service.UserService;
import com.wonders.mr.util.Constant;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

	private static Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(UserPO userPO) {
		userDao.save(userPO);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(UserPO userPO) {
		userDao.update(userPO);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Long id) {
		UserPO userPO = findById(id);
		userPO.setDeleteFlag(Constant.USER_DELETE);
		update(userPO);
	}

	@Override
	public UserPO findById(Long id) {
		return userDao.get(id);
	
	}

	@Override
	public UserPO findByLoginName(String loginName) {
		Map<String, Object> queryMap = new HashMap<>();
		
		queryMap.put("deleteFlag", Constant.USER_NOT_DELETE);
		queryMap.put("loginName", loginName);
		List<UserPO> list =userDao.findBy(queryMap);
		return list.size() > 1 ? list.get(0):null; 
		
	}

	@Override
	public List<UserPO> findAll() {
		return userDao.findAll();
	}
}
