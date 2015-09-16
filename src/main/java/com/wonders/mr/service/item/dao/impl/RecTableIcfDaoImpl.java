package com.wonders.mr.service.item.dao.impl;

import org.springframework.stereotype.Repository;

import com.wonders.bud.framework.common.hibernate.HibernateBaseDaoImpl;
import com.wonders.mr.service.item.dao.RecTableIcfDao;
import com.wonders.mr.service.item.modal.po.RecTableIcfPO;

@Repository("recTableIcfDaoImpl")
public class RecTableIcfDaoImpl extends HibernateBaseDaoImpl<RecTableIcfPO, Long>implements RecTableIcfDao {

}
