package com.wonders.mr.service.item.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wonders.mr.service.item.dao.TagDao;
import com.wonders.mr.service.item.modal.po.TagPO;
import com.wonders.mr.service.item.service.TagService;

@Service("tagServiceImpl")
public class TagServiceImpl implements TagService {

	@Autowired
	private TagDao tagDao;

	@Override
	public List<TagPO> findBySymptom(String symptom) {
		Map<String, String> like = new HashMap<String, String>();
		like.put("symptom", symptom);
		List<TagPO> result = tagDao.findByLike(like);
		return result;
	}

	@Override
	public List<TagPO> findSpecial(long[] ids) {
		
		 return tagDao.findByIds(ids);
		
	}

	@Override
	public TagPO findByTagId(long tagId) {
		return tagDao.get(tagId);
	}
	
	

}
