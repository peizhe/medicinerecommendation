package com.wonders.mr.web.pages.index.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wonders.bud.framework.common.util.RestMsg;
import com.wonders.mr.service.item.modal.po.TagPO;
import com.wonders.mr.service.item.service.TagService;


@Controller
@RequestMapping("pages/index/controller")
public class indexPageController {
	protected Logger log = Logger.getLogger(indexPageController.class);
	
	@Resource
	private TagService tagService;
	
	/**
	 * 获取tag标签
	 */
	@RequestMapping(value = "/getTagInfo", method = RequestMethod.POST)
	@ResponseBody
	public RestMsg<List<Map<String, Object>>> getTagInfo(HttpServletRequest request) {
		RestMsg<List<Map<String, Object>>> rm = new RestMsg<List<Map<String, Object>>>();
		
		try {
			List<TagPO> tagPOs = tagService.findAll();
			if(tagPOs==null||tagPOs.size()==0) {
				rm.setMsg("nothing");
				return rm;
			}
			List<Map<String, Object>> list = new ArrayList<>();
			for(TagPO tagPO:tagPOs) {
				Map<String, Object> tag = new HashMap<>();
				tag.put("tagId",tagPO.getTagId());
				tag.put("symptom", tagPO.getSymptom());
				list.add(tag);
			}
			rm.setResult(list);
			rm.setMsg("success");
		} catch (Exception exception) {
			// TODO: handle exception
			rm.setMsg("error");
			exception.printStackTrace();
			return rm;
		}
		return rm;
	}

}
