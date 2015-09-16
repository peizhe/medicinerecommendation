package com.wonders.mr.service.action.dao.impl;

import org.springframework.stereotype.Repository;

import com.wonders.bud.framework.common.hibernate.HibernateBaseDaoImpl;
import com.wonders.mr.service.action.dao.ActionDao;
import com.wonders.mr.service.action.modal.ActionPO;

/**
 * 用户用药dao实现类
 * @author xh
 *
 */
@Repository("actionDaoImpl")
public class ActionDaoImpl extends HibernateBaseDaoImpl<ActionPO, Long> implements ActionDao{

}
