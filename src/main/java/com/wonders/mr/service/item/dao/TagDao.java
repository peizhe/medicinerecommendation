package com.wonders.mr.service.item.dao;

import java.util.List;

import com.wonders.bud.framework.common.hibernate.HibernateBaseDao;
import com.wonders.mr.service.item.modal.po.TagPO;

public interface TagDao extends HibernateBaseDao<TagPO, Long>{
	/**
	 * 根据tagId查询tag表下的指定数组id下的symptom
	 * @param ids 数据目录id
	 * @return
	 */
	public List<TagPO> findByIds(long[] ids);

}
