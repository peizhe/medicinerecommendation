package com.wonders.mr.service.recitemtagsim.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wonders.bud.framework.common.hibernate.HibernateBaseDaoImpl;
import com.wonders.bud.framework.common.page.Page;
import com.wonders.bud.framework.common.util.QueryBaseParam;
import com.wonders.bud.framework.common.util.QueryParam;
import com.wonders.mr.service.recitemtagsim.dao.RecItemTagSimDao;
import com.wonders.mr.service.recitemtagsim.modal.RecItemTagSimPO;

@Repository("RecItemTagSimDaoImpl")
public class RecItemTagSimDaoImpl extends HibernateBaseDaoImpl<RecItemTagSimPO, Long> implements RecItemTagSimDao {

	@Override
	public RecItemTagSimPO save(RecItemTagSimPO po) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecItemTagSimPO update(RecItemTagSimPO po) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecItemTagSimPO get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecItemTagSimPO get(Long id, boolean lock) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(RecItemTagSimPO paramT) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<RecItemTagSimPO> findAll() {
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
	public List<RecItemTagSimPO> findBy(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecItemTagSimPO> findByLike(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecItemTagSimPO> findByProperty(String property, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecItemTagSimPO findUniqueByProperty(String property, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<RecItemTagSimPO> findByPage(Page<RecItemTagSimPO> page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<RecItemTagSimPO> findByPage(Page<RecItemTagSimPO> page, String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecItemTagSimPO> findByAnd(QueryBaseParam param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecItemTagSimPO> findByAnd(QueryParam param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecItemTagSimPO> findByOr(QueryBaseParam param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecItemTagSimPO> findByOr(QueryParam param) {
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
