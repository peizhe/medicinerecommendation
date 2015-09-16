package com.wonders.mr.service.item.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.wonders.bud.framework.common.hibernate.HibernateBaseDaoImpl;
import com.wonders.mr.service.item.dao.TagDao;
import com.wonders.mr.service.item.modal.po.TagPO;

@Repository("tagDaoImpl")
public class TagDaoImpl extends HibernateBaseDaoImpl<TagPO, Long> implements TagDao{
	@Override
	public List<TagPO> findByIds(long[] ids) {

		Session session = getSession();
		String hql = "from TagPO where tagId in (:ids)";

		// 将long转为Long
		Long[] idsL = new Long[ids.length];
		for (int i = 0; i < ids.length; i++) {
			idsL[i] = ids[i];
		}
		
		Query query = session.createQuery(hql);
		query.setParameterList("ids", idsL);
		@SuppressWarnings("unchecked")
		List<TagPO> result = query.list();
		return result;
	}

}
