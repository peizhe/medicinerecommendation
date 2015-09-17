package com.wonders.mr.web.pages.product.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wonders.bud.framework.common.util.RestMsg;
import com.wonders.mr.service.item.modal.po.ItemPO;
import com.wonders.mr.service.item.service.ItemService;
import com.wonders.mr.service.item.service.TagItemService;
import com.wonders.mr.service.recitemcfsim.service.RecItemCfSimService;


@Controller
@RequestMapping("pages/product/controller")
public class prodcutPageController {
	protected Logger log = Logger.getLogger(prodcutPageController.class);
	
	@Resource
	private TagItemService tagItemService;

	@Autowired
	private RecItemCfSimService recItemCfSimService;

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

	/*
	 * 
	 * 用过该药的用户还用过哪些药
	 * */
	@RequestMapping(value = "/getItem", method = RequestMethod.POST)
	@ResponseBody
	public RestMsg<String> getItem(HttpServletRequest request){
		RestMsg<String> rm=new RestMsg<>();
		String htmlStr="";
		try {
			String itemId=request.getParameter("itemId");
			ItemPO selfPo=itemService.findById(Long.parseLong(itemId));
			if(selfPo!=null){

				StringBuilder str=new StringBuilder();
				str.append("<div style=\" background-color:#F97E76;padding:3px;color: white;padding-left: 48px; margin-top:10px;\"><h3>产品详细介绍：</h3></div>");
				str.append("<div>");
				str.append("<div style=\"float: left; width: 300px\"><img alt=\"\" src=\""+selfPo.getImgUrl()+"\" style=\"min-width: 300px;\"></div>");
				str.append("<div style=\"float: left; margin-left: 100px; padding-top: 45px; width: 500px;\">");
				
				str.append("<div class=\"itemInfo\"><lable class=\"intag\">名称：</lable>"+selfPo.getItemName()+"</div>");
				str.append("<div class=\"itemInfo\"><lable class=\"intag\">价格：</lable>"+selfPo.getPrice()+"</div>");
				str.append("<div class=\"itemInfo\"><lable class=\"intag\">分类：</lable>"+selfPo.getCategory()+"</div>");
				str.append("<div class=\"itemInfo\"><lable class=\"intag\">用途：</lable><p>"+selfPo.getsymptomDesc()+"</p></div>");
				str.append("<div class=\"itemInfo\"><lable class=\"intag\">主要成分：</lable>"+selfPo.getComponent()+"</div>");
				str.append("<div class=\"itemInfo\"><lable class=\"intag\">生产商：</lable>"+selfPo.getCompany()+"</div>");
				str.append("<div><a class=\"now-get get-cart\" style=\"margin-top: 10px;float: left;\" onclick=\"addToCart("+selfPo.getItemId()+")\">加入购物车</a></div>");
				str.append("</div>");
				str.append("</div>");
				str.append("<div class=\"women-product\">");
				str.append("<div class=\"products\">");
				str.append("<h5 class=\"latest-product\">用过该药的用户还用过以下药物</h5>");
				str.append("</div>");
				str.append("</div>");
				str.append("<div class=\"clearfix\"></div>");
				str.append("</div>");
				htmlStr=str.toString();
				rm.setMsg("success");
				rm.setResult(htmlStr);
			}
		} catch (Exception e) {
			rm.setMsg("error");
			e.printStackTrace();
			return rm;
		}
		return rm;
	}
	

	/*
	 * 
	 * 用过该药的用户还用过哪些药
	 * */
	@RequestMapping(value = "/getRecItemCfSim", method = RequestMethod.POST)
	@ResponseBody
	public RestMsg<String> getRecItemCfSim(HttpServletRequest request){
		RestMsg<String> rm=new RestMsg<>();
		String htmlStr="";
		try {
			String itemId=request.getParameter("itemId");
			List<ItemPO> items=recItemCfSimService.findByItemId(Long.parseLong(itemId));
			if(items!=null&&items.size()>0){
				int limit=items.size();
				StringBuilder str=new StringBuilder();
				for(int i=0;i<limit;i++){
					ItemPO item=items.get(i);
					String link="single2.html?itemId="+item.getItemId();				
					str.append("<div class=\"  product-grid\">");
					str.append("<div class=\"content_box\">");
					str.append("<a href=\""+link+"\">");
					str.append("</a><div class=\"left-grid-view grid-view-left\"><a href=\""+link+"\">");
					str.append("<img src=\""+item.getImgUrl()+"\" class=\"img-responsive watch-right\" alt=\"\">");
					str.append("<div class=\"mask\">");
					str.append("<div class=\"info\">Quick View</div>");
					str.append("</div>");
					str.append("</a>");
					str.append("</div>");
					str.append("<h4>");
					str.append("<a href=\""+link+"\"> "+item.getItemName()+"</a>");
					str.append("</h4>");
					str.append("<p>"+item.getCategory()+"</p>");
					str.append("<div style=\"margin-left: 30px;float: left;\"><a class=\"now-get get-cart\" href=\""+link+"\">加入购物车</a></div>");
					str.append("</div>");
					str.append("</div>");					
				}	
				htmlStr=str.toString();
				rm.setMsg("success");
				rm.setResult(htmlStr);
			}
		} catch (Exception e) {
			rm.setMsg("error");
			e.printStackTrace();
			return rm;
		}
		return rm;
	}
}
