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

import com.wonders.bud.framework.common.page.Page;
import com.wonders.bud.framework.common.util.RestMsg;
import com.wonders.mr.service.item.modal.po.ItemPO;
import com.wonders.mr.service.item.modal.po.TagPO;
import com.wonders.mr.service.item.service.ItemService;
import com.wonders.mr.service.item.service.TagItemService;
import com.wonders.mr.service.item.service.TagService;
import com.wonders.mr.service.recitemcfsim.service.RecItemCfSimService;
import com.wonders.mr.service.recitemtagsim.service.RecItemTagSimService;


@Controller
@RequestMapping("pages/product/controller")
public class ProdcutPageController {
	protected Logger log = Logger.getLogger(ProdcutPageController.class);
	
	@Resource
	private TagItemService tagItemService;

	@Autowired
	private RecItemCfSimService recItemCfSimService;

	@Resource
	private ItemService itemService;
	
	@Autowired
	private RecItemTagSimService recItemTagSimService;
	
	@Autowired
	private TagService tagservice;
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
		if(tagIds==null||tagIds.trim().equals("")) {
			rm.setMsg("noTagId");
			return rm;
		}
		Long tagId = Long.parseLong(tagIds);
		String currentPages = request.getParameter("currentPage");
		int pageNum=9;
		int currentPage = Integer.parseInt(currentPages);
		int currentList = (currentPage-1)*pageNum;
		try {
			Page<ItemPO> itemPages= tagItemService.findById(tagId,currentList,pageNum);
			List<ItemPO> itemPOs=itemPages.getResult();
			TagPO tagPO= tagservice.findByTagId(tagId);
			int total=itemPages.getTotal();
			int totalPage=total%pageNum==0? total/9:total/9 +1;
			List<Map<String, Object>> list = new ArrayList<>();
			int limit = 0;
			if(currentPage == totalPage) {
				limit = total - (currentPage - 1) * pageNum;
			}else{
				limit = pageNum;
			}
			
			
			for(int i=0;i<limit;i++) {
				Map<String, Object> tag = new HashMap<>();
				tag.put("itemId",itemPOs.get(i).getItemId());
				tag.put("itemName", itemPOs.get(i).getItemName());
				tag.put("company", itemPOs.get(i).getCompany());
				tag.put("price", itemPOs.get(i).getPrice());
				tag.put("imgUrl", itemPOs.get(i).getImgUrl());
				list.add(tag);
			}
			Map<String, Object> tagMap = new HashMap<>();
			tagMap.put("tagName", tagPO.getSymptom());
			list.add(tagMap);
			rm.setResult(list);
			rm.setMsg("success");
			rm.setCode(totalPage);
		} catch (Exception exception) {
			rm.setMsg("error");
			exception.printStackTrace();
			return rm;
		}
		return rm;
		
	}

/*
	*//**
	 * 获得tagId下治疗本病症的药品总数的页数
	 * @param request
	 * @return
	 *//*
	@RequestMapping(value = "/getAllPage", method = RequestMethod.GET)
	@ResponseBody
	public RestMsg<String> getAllPage(HttpServletRequest request) {
		RestMsg<String> rm = new RestMsg<String>();
		String tagIds = request.getParameter("tagId");
		Long tagId = Long.parseLong(tagIds);
		List<ItemPO> itemPOs = tagItemService.findById(tagId);
		int allNums = itemPOs.size();
		int allPageNums;
		if(allNums%9==0) {
			allPageNums = allNums/9;
		}else {
			allPageNums = allNums/9+1;
		}
		
		String allPageNum = allPageNums+"";
		rm.setMsg(allPageNum);
		return rm;
	}
	*/
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
				str.append("<div>");
				str.append("<div style=\"float: left; width: 300px\"><img alt=\"\" src=\""+selfPo.getImgUrl()+"\" style=\"min-width: 300px;\"></div>");
				str.append("<div style=\"float: left; margin-left: 100px; padding-top: 45px; width: 500px;\">");				
				str.append("<div class=\"itemInfo\"><lable class=\"intag\">名称：</lable>"+selfPo.getItemName()+"</div>");
				str.append("<div class=\"itemInfo\"><lable class=\"intag\">价格：</lable>"+selfPo.getPrice()+"</div>");
				str.append("<div class=\"itemInfo\"><lable class=\"intag\">分类：</lable>"+selfPo.getCategory()+"</div>");
				str.append("<div class=\"itemInfo\"><lable class=\"intag\">用途：</lable><p>"+selfPo.getsymptomDesc()+"</p></div>");
				str.append("<div class=\"itemInfo\"><lable class=\"intag\">主要成分：</lable>"+selfPo.getComponent()+"</div>");
				str.append("<div class=\"itemInfo\"><lable class=\"intag\">生产商：</lable>"+selfPo.getCompany()+"</div>");
				str.append("<div onclick=\"addToShoppingCart("+selfPo.getItemId()+"\")><a class=\"now-get get-cart\" style=\"margin-top: 10px;\">加入购物车</a></div>");
				str.append("</div>");
				str.append("</div>");
				str.append("<div class=\"women-product\">");			
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
	 * 类似功能药品
	 * */
	@RequestMapping(value = "/getRecItemTagSim", method = RequestMethod.POST)
	@ResponseBody
	public RestMsg<String> getRecItemTagSim(HttpServletRequest request){
		RestMsg<String> rm=new RestMsg<>();
		String htmlStr="";
		try {
			String itemId=request.getParameter("itemId");
			String page=request.getParameter("batch");
			if(itemId!=null&&page!=null){
				
				int batch=Integer.parseInt(page);
				List<ItemPO> items=recItemTagSimService.findByItemId(Long.parseLong(itemId));
				if(items!=null&&items.size()>0){
					int count=5;
					int total=items.size();
					int totalPage=total%count==0?total/count:(total/count+1);
					int start=count*(batch-1);
					int end=count*batch<total?count*batch:total;
					StringBuilder str=new StringBuilder();
					for(int i=start;i<end;i++){
						ItemPO item=items.get(i);
						String link="single2.html?itemId="+item.getItemId();				
						str.append("<div class=\"s-item-wrap\">");	
						str.append("<div class=\"s-item\">");	
						str.append("<div class=\"s-top-hover\">");	
						str.append("<a target=\"_blank\" data-spm=\"d4920134\" href=\"\" class=\"i-goto-similar\">找相似</a>");	
						str.append("</div>");	
						str.append("<div class=\"s-pic\">");	
						str.append("<a href=\""+link+"\" target=\"_blank\" class=\"s-pic-link\" data-spm=\"d4919530\"> ");	
						str.append("<img src=\""+item.getImgUrl()+"\" alt=\""+item.getsymptomDesc()+"\" title=\""+item.getItemName()+"\" class=\"s-pic-img s-guess-item-img\">");	
						str.append("</a>");	
						str.append("</div>");	
						str.append("<div class=\"s-price-box\">");	
						str.append("<span class=\"s-price\"><em class=\"s-price-sign\">¥</em><em class=\"s-value\">"+item.getPrice()+"</em></span> <span class=\"s-history-price\"><em class=\"s-price-sign\"></em><em class=\"s-value\"></em></span>");	
						str.append("</div>");	
						str.append("<div class=\"s-title\">");	
						str.append("<a href=\"\" title=\""+item.getItemName()+"\" target=\"_blank\" data-spm=\"d4919530\">"+item.getItemName()+"</a>");	
						str.append("</div>");	
						str.append("<div class=\"s-extra-box\">");	
						str.append("<span class=\"s-comment\"></span> <span class=\"s-sales mouseOvers\"><button style=\"color: black;font-weight: bold;\" onclick=\"addToShoppingCart("+item.getItemId()+")\">加入购物车</button></span>");	
						str.append("</div>");	
						str.append("</div>");	
						str.append("</div>");		
					}	
					htmlStr=str.toString();
					rm.setMsg("success");
					rm.setResult(htmlStr);
					rm.setCode(totalPage);
				}
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
			String page=request.getParameter("batch");
			if(itemId!=null&&page!=null){
				
				int batch=Integer.parseInt(page);
				List<ItemPO> items=recItemCfSimService.findByItemId(Long.parseLong(itemId));
				if(items!=null&&items.size()>0){
					int count=5;
					int total=items.size();
					int totalPage=total%count==0?total/count:(total/count+1);
					int start=count*(batch-1);
					int end=count*batch<total?count*batch:total;
					StringBuilder str=new StringBuilder();
					for(int i=start;i<end;i++){
						ItemPO item=items.get(i);
						String link="single2.html?itemId="+item.getItemId();				
						str.append("<div class=\"s-item-wrap\">");	
						str.append("<div class=\"s-item\">");	
						str.append("<div class=\"s-top-hover\">");	
						str.append("<a target=\"_blank\" data-spm=\"d4920134\" href=\"\" class=\"i-goto-similar\">找相似</a>");	
						str.append("</div>");	
						str.append("<div class=\"s-pic\">");	
						str.append("<a href=\""+link+"\" target=\"_blank\" class=\"s-pic-link\" data-spm=\"d4919530\"> ");	
						str.append("<img src=\""+item.getImgUrl()+"\" alt=\""+item.getsymptomDesc()+"\" title=\""+item.getItemName()+"\" class=\"s-pic-img s-guess-item-img\">");	
						str.append("</a>");	
						str.append("</div>");	
						str.append("<div class=\"s-price-box\">");	
						str.append("<span class=\"s-price\"><em class=\"s-price-sign\">¥</em><em class=\"s-value\">"+item.getPrice()+"</em></span> <span class=\"s-history-price\"><em class=\"s-price-sign\">¥</em><em class=\"s-value\"></em></span>");	
						str.append("</div>");	
						str.append("<div class=\"s-title\">");	
						str.append("<a href=\"\" title=\""+item.getItemName()+"\" target=\"_blank\" data-spm=\"d4919530\">"+item.getItemName()+"</a>");	
						str.append("</div>");	
						str.append("<div class=\"s-extra-box\">");	
						str.append("<span class=\"s-comment\"></span> <span class=\"s-sales\"><button style=\"color: black;font-weight: bold;\" onclick=\"addToShoppingCart("+item.getItemId()+")\">加入购物车</button></span>");	
						str.append("</div>");	
						str.append("</div>");	
						str.append("</div>");		
					}	
					htmlStr=str.toString();
					rm.setMsg("success");
					rm.setResult(htmlStr);
					rm.setCode(totalPage);
				}
			}	
		} catch (Exception e) {
			rm.setMsg("error");
			e.printStackTrace();
			return rm;
		}
		return rm;
	}
}
