package com.wonders.mr.service.recusersim.dao.impl;

import org.springframework.stereotype.Repository;
import com.wonders.bud.framework.common.hibernate.HibernateBaseDaoImpl;
import com.wonders.mr.service.recusersim.dao.RecUserSimDao;
import com.wonders.mr.service.recusersim.modal.RecUserSimPO;

/**
 * 找病友dao实现
 * 
 * @author lixiang
 *
 */
@Repository("recUserSimDaoImpl")
public class RecUserSimDaoImpl extends HibernateBaseDaoImpl<RecUserSimPO, Long> implements RecUserSimDao {

}
