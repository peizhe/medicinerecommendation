package com.wonders.mr.web.pages.userInfo.controller;

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
import com.wonders.mr.service.recitemcfsim.service.RecItemCfSimService;


@Controller
@RequestMapping("pages/userInfo/controller")
public class UserInfoController {
	protected Logger log = Logger.getLogger(UserInfoController.class); 
	@Autowired
	private RecItemCfSimService recItemCfSimService;
	
	@RequestMapping(value = "/getRecItemCfSim", method = RequestMethod.POST)
	@ResponseBody
	public RestMsg<String> getRecItemCfSim(HttpServletRequest request){
		RestMsg<String> rm=new RestMsg<>();
		String htmlStr="";
		try {
			String userId=request.getParameter("userId");
			List<ItemPO> items=recItemCfSimService.findByItemId(Long.parseLong(userId));
			if(items!=null&&items.size()>0){
				int limit=items.size();
				StringBuilder str=new StringBuilder();
				for(int i=0;i<limit;i++){
					ItemPO item=items.get(i);
					str.append("<div class=\"  product-grid\">");
					str.append("<div class=\"content_box\">");
					str.append("<a href=\"single.html\">");
					str.append("</a><div class=\"left-grid-view grid-view-left\"><a href=\"single.html\">");
					str.append("<img src=\"images/pic13.jpg\" class=\"img-responsive watch-right\" alt=\"\">");
					str.append("<div class=\"mask\">");
					str.append("<div class=\"info\">Quick View</div>");
					str.append("</div>");
					str.append("</a>");
					str.append("</div>");
					str.append("<h4>");
					str.append("<a href=\"#\"> Duis autem</a>");
					str.append("</h4>");
					str.append("<p>It is a long established fact that a reader</p>");
					str.append("Rs. 499");
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
