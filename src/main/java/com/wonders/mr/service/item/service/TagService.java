package com.wonders.mr.service.item.service;

import java.util.List;

import com.wonders.mr.service.item.modal.po.TagPO;

public interface TagService {

	/**
	 * 通过symptom模糊搜索Tag
	 * @param symptom
	 * @return
	 */
	public List<TagPO> findBySymptom(String symptom);
	
	/**
	 * 查找所有的Tag
	 * @param long[] ids 是获取有限个数tag记录的一组数组
	 * @return
	 */
	public List<TagPO> findSpecial(long[] ids);
	
	public TagPO findByTagId(long tagId);
	
}
