package com.wonders.mr.service.user.dao.impl;

import org.springframework.stereotype.Repository;

import com.wonders.bud.framework.common.hibernate.HibernateBaseDaoImpl;
import com.wonders.mr.service.user.dao.UserDao;
import com.wonders.mr.service.user.modal.UserPO;

@Repository("userDaoImpl")
public class UserDaoImpl  extends HibernateBaseDaoImpl<UserPO, Long> implements UserDao{

}
