package com.wonders.mr.web.pages.product.controller;

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
import com.wonders.mr.service.item.modal.po.ItemPO;
import com.wonders.mr.service.item.service.ItemService;
import com.wonders.mr.service.item.service.TagItemService;


@Controller
@RequestMapping("pages/product/controller")
public class prodcutPageController {
	protected Logger log = Logger.getLogger(prodcutPageController.class);
	
	@Resource
	private TagItemService tagItemService;
	
	@Resource
	private ItemService itemService;
	/**
	 * 通过tagId查找适用于治疗本病症的药品集
	 * @param tagId
	 * @param request
	 * @return 
	 */
	@RequestMapping(value = "/getMedSet", method = RequestMethod.GET)
	@ResponseBody
	public RestMsg<List<Map<String, Object>>> getMedSet(HttpServletRequest request) {
		RestMsg<List<Map<String, Object>>> rm = new RestMsg<List<Map<String, Object>>>();
		String tagIds = request.getParameter("tagId");
		Long tagId = Long.parseLong(tagIds);
		try {
			List<ItemPO> itemPOs = tagItemService.findById(tagId);
			if(itemPOs==null||itemPOs.size()==0) {
				rm.setMsg("nothing");
				return rm;
			}
			List<Map<String, Object>> list = new ArrayList<>();
			for(ItemPO itemPO:itemPOs) {
				Map<String, Object> tag = new HashMap<>();
				tag.put("itemId",itemPO.getItemId());
				tag.put("itemName", itemPO.getItemName());
				tag.put("company", itemPO.getCompany());
				tag.put("price", itemPO.getPrice());
				tag.put("imgUrl", itemPO.getImgUrl());
				list.add(tag);
			}
			rm.setResult(list);
			rm.setMsg("success");
		} catch (Exception exception) {
			rm.setMsg("error");
			exception.printStackTrace();
			return rm;
		}
		return rm;
		
	}

}
