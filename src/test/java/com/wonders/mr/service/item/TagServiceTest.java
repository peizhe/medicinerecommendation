package com.wonders.mr.service.item;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wonders.bud.framework.common.AbstractTestCase;
import com.wonders.mr.service.item.modal.po.TagPO;
import com.wonders.mr.service.item.service.TagService;

public class TagServiceTest extends AbstractTestCase{

	@Autowired
	private TagService tagService;
	
	@Test
	public void findBySymptomTest() {
		
		List<TagPO> result = tagService.findBySymptom("感冒");
		
		Assert.assertEquals(3, result.size());
		
		
	}
	
}
