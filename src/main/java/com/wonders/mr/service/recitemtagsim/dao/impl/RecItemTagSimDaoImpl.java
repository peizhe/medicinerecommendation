package com.wonders.mr.service.recitemtagsim.dao.impl;


import org.springframework.stereotype.Repository;

import com.wonders.bud.framework.common.hibernate.HibernateBaseDaoImpl;
import com.wonders.mr.service.recitemtagsim.dao.RecItemTagSimDao;
import com.wonders.mr.service.recitemtagsim.modal.RecItemTagSimPO;

/**
 * 物品相似度Dao实现
 * 
 * @author lixiang
 *
 */
@Repository("recItemTagSimDaoImpl")
public class RecItemTagSimDaoImpl extends HibernateBaseDaoImpl<RecItemTagSimPO, Long> implements RecItemTagSimDao {

}
