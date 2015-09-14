package com.wonders.mr.service.item.dao.impl;

import org.springframework.stereotype.Repository;

import com.wonders.bud.framework.common.hibernate.HibernateBaseDaoImpl;
import com.wonders.mr.service.item.dao.TagItemDao;
import com.wonders.mr.service.item.modal.po.TagItemPO;

/**
 * 
 * @author xh
 *
 */
@Repository("tagItemDaoImpl")
public class TagItemDaoImpl extends HibernateBaseDaoImpl<TagItemPO, Long> implements TagItemDao{

}
