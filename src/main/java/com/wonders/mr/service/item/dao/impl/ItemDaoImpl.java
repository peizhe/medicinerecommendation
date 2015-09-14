package com.wonders.mr.service.item.dao.impl;

import org.springframework.stereotype.Repository;

import com.wonders.bud.framework.common.hibernate.HibernateBaseDaoImpl;
import com.wonders.mr.service.item.dao.ItemDao;
import com.wonders.mr.service.item.modal.po.ItemPO;

/**
 * 药品接口实现类
 * @author xh
 *
 */
@Repository("itemDaoImpl")
public class ItemDaoImpl extends HibernateBaseDaoImpl<ItemPO, Long> implements ItemDao{

}
