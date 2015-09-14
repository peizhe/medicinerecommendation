package com.wonders.mr.service.item.dao.impl;

import org.springframework.stereotype.Repository;

import com.wonders.bud.framework.common.hibernate.HibernateBaseDaoImpl;
import com.wonders.mr.service.item.dao.RecTableUcfDao;
import com.wonders.mr.service.item.modal.po.RecTableUcfPO;

@Repository("recTableUcfDaoImpl")
public class RecTableUcfDaoImpl extends HibernateBaseDaoImpl<RecTableUcfPO, Long> implements RecTableUcfDao{

}
