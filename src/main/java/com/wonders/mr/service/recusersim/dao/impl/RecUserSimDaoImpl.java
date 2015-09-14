package com.wonders.mr.service.recusersim.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wonders.bud.framework.common.hibernate.HibernateBaseDaoImpl;
import com.wonders.bud.framework.common.page.Page;
import com.wonders.bud.framework.common.util.QueryBaseParam;
import com.wonders.bud.framework.common.util.QueryParam;
import com.wonders.mr.service.recusersim.dao.RecUserSimDao;
import com.wonders.mr.service.recusersim.modal.RecUserSimPO;

@Repository("RecUserSimDaoImpl")
public class RecUserSimDaoImpl extends HibernateBaseDaoImpl<RecUserSimPO, Long> implements RecUserSimDao {

	@Override
	public RecUserSimPO save(RecUserSimPO po) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecUserSimPO update(RecUserSimPO po) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecUserSimPO get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecUserSimPO get(Long id, boolean lock) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(RecUserSimPO paramT) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<RecUserSimPO> findAll() {
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
	public List<RecUserSimPO> findBy(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecUserSimPO> findByLike(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecUserSimPO> findByProperty(String property, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecUserSimPO findUniqueByProperty(String property, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<RecUserSimPO> findByPage(Page<RecUserSimPO> page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<RecUserSimPO> findByPage(Page<RecUserSimPO> page, String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecUserSimPO> findByAnd(QueryBaseParam param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecUserSimPO> findByAnd(QueryParam param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecUserSimPO> findByOr(QueryBaseParam param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecUserSimPO> findByOr(QueryParam param) {
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
