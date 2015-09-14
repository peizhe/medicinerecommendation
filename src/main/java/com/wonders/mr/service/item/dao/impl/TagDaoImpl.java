package com.wonders.mr.service.item.dao.impl;

import org.springframework.stereotype.Repository;

import com.wonders.bud.framework.common.hibernate.HibernateBaseDaoImpl;
import com.wonders.mr.service.item.dao.TagDao;
import com.wonders.mr.service.item.modal.po.TagPO;

@Repository("tagDaoImpl")
public class TagDaoImpl extends HibernateBaseDaoImpl<TagPO, Long> implements TagDao{

}
