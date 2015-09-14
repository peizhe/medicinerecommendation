package com.wonders.mr.service.recitemcfsim.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wonders.bud.framework.common.hibernate.HibernateBaseDaoImpl;
import com.wonders.bud.framework.common.page.Page;
import com.wonders.bud.framework.common.util.QueryBaseParam;
import com.wonders.bud.framework.common.util.QueryParam;
import com.wonders.mr.service.recitemcfsim.dao.RecItemCfSimDao;
import com.wonders.mr.service.recitemcfsim.modal.RecItemCfSimPO;

@Repository("RecItemCfSimDaoImpl")
public class RecItemCfSimDaoImpl extends HibernateBaseDaoImpl<RecItemCfSimPO, Long> implements RecItemCfSimDao {

	@Autowired
	private RecItemCfSimDao recItemCfSimDao;
	
	@Override
	public RecItemCfSimPO save(RecItemCfSimPO po) {
		
		return recItemCfSimDao.save(po);
	}

	@Override
	public RecItemCfSimPO update(RecItemCfSimPO po) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecItemCfSimPO get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecItemCfSimPO get(Long id, boolean lock) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(RecItemCfSimPO paramT) {
		// TODO Auto-generated method stub

	}

	@Osverride
	public void deleteById(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<RecItemCfSimPO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> find(String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object findUnique(String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecItemCfSimPO> findBy(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecItemCfSimPO> findByLike(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecItemCfSimPO> findByProperty(String property, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecItemCfSimPO findUniqueByProperty(String property, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<RecItemCfSimPO> findByPage(Page<RecItemCfSimPO> page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<RecItemCfSimPO> findByPage(Page<RecItemCfSimPO> page, String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecItemCfSimPO> findByAnd(QueryBaseParam param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecItemCfSimPO> findByAnd(QueryParam param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecItemCfSimPO> findByOr(QueryBaseParam param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecItemCfSimPO> findByOr(QueryParam param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean duplicatecheck(String idProperty, Object id, Map<String, Object> map) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object findMax(String max, Map<String, Object> Map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object findMin(String min, Map<String, Object> Map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object findAvg(String avg, Map<String, Object> Map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object findSum(String sum, Map<String, Object> Map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] findStatisticByAnd(QueryParam param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] findStatisticByOr(QueryParam param) {
		// TODO Auto-generated method stub
		return null;
	}

}
