package com.wonders.mr.web.pages.product.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wonders.bud.framework.common.util.RestMsg;
import com.wonders.mr.service.item.modal.po.ItemPO;
import com.wonders.mr.service.item.service.ItemService;
import com.wonders.mr.service.recitemcfsim.service.RecItemCfSimService;

@Controller
@RequestMapping("pages/product/controller")
public class prodcutPageController {
	protected Logger log = Logger.getLogger(prodcutPageController.class);
	@Autowired
	private RecItemCfSimService recItemCfSimService;
	private ItemService itemService;
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
			ItemPO selfPo=itemService.findById(Long.parseLong(itemId));
			List<ItemPO> items=recItemCfSimService.findByItemId(Long.parseLong(itemId));
			if(items!=null&&items.size()>0){
				int limit=items.size();
				StringBuilder str=new StringBuilder();
				str.append("<div><h3>产品详细介绍：</h3></div>");
				str.append("<div>");
				str.append("<div style=\"float: left; width: 300px\"><img alt=\"\" src=\""+selfPo.getImgUrl()+"\" style=\"min-width: 300px;\"></div>");
				str.append("<div style=\"float: left; margin-left: 20px; padding-top: 45px;\">");
				str.append("<div>名称："+selfPo.getItemName()+"</div><div>价格：￥"+selfPo.getPrice()+"</div><div>分类："+selfPo.getCategory()+"</div><div>适用于：<p>"+selfPo.getsymptomDesc()+"</p></div><div>主要成分：<p>"+selfPo.getComponent()+"</p></div><div>生产商："+selfPo.getCompany()+"</div>");
				str.append("</div>");
				str.append("</div>");
				str.append("<div class=\"women-product\">");
				str.append("<div class=\"grid-product\" style=\"margin: 5% 0;\">");
				
				
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
					str.append("");
					str.append("</div>");
					str.append("</div>");					
				}	
				str.append("<div class=\"clearfix\"></div>");
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
}
