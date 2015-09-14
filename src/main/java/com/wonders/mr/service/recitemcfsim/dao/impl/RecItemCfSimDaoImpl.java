package com.wonders.mr.service.recitemcfsim.dao.impl;


import org.springframework.stereotype.Repository;

import com.wonders.bud.framework.common.hibernate.HibernateBaseDaoImpl;
import com.wonders.mr.service.recitemcfsim.dao.RecItemCfSimDao;
import com.wonders.mr.service.recitemcfsim.modal.RecItemCfSimPO;

/**
 * 用过该药的用户还用过什么药dao实现
 * 
 * @author lixiang
 *
 */
@Repository("recItemCfSimDaoImpl")
public class RecItemCfSimDaoImpl extends HibernateBaseDaoImpl<RecItemCfSimPO, Long>  implements RecItemCfSimDao {


}
