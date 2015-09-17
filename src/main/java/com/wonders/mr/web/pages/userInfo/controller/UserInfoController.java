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
import com.wonders.mr.service.action.service.ActionService;
import com.wonders.mr.service.item.modal.po.ItemPO;
import com.wonders.mr.service.recitemcfsim.service.RecItemCfSimService;
import com.wonders.mr.service.user.modal.UserPO;
import com.wonders.mr.service.user.service.UserService;


@Controller
@RequestMapping("pages/userInfo/controller")
public class UserInfoController {
	protected Logger log = Logger.getLogger(UserInfoController.class); 
	@Autowired
	private RecItemCfSimService recItemCfSimService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ActionService actionService;
	
	@RequestMapping(value = "/getAllItemsByUserId", method = RequestMethod.POST)
	@ResponseBody
	public RestMsg<String> getAllItemsByUserId(HttpServletRequest request){
		RestMsg<String> rm=new RestMsg<>();
		String htmlStr="";
		try {
			String userId=request.getParameter("userId");
			List<ItemPO> items=actionService.findByUserId(Long.parseLong(userId));
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
					str.append("<div style=\"margin-left: 30px;float: left;padding-left:196px;\"><a class=\"now-get get-cart\" href=\""+link+"\">加入购物车</a></div>");
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
	
	/*
	 * 
	 * 用过该药的用户还用过哪些药
	 * */
	@RequestMapping(value = "/getUser", method = RequestMethod.POST)
	@ResponseBody
	public RestMsg<String> getUser(HttpServletRequest request){
		RestMsg<String> rm=new RestMsg<>();
		String htmlStr="";
		try {
			String userId=request.getParameter("userId");
			UserPO user=userService.findById(Long.parseLong(userId));
			if(user!=null){
				String ImgUrl="images/user.jpg";
				StringBuilder str=new StringBuilder();
				String sex=user.getOccupation()=="F"?"女":"男";
				str.append("<div style=\" background-color:#F97E76;padding:3px;color: white;padding-left: 48px; margin-top:10px;\"><h3>用户个人详情</h3></div>");
				str.append("<div>");
				str.append("<div style=\"float: left; width: 300px\"><img alt=\"\" src=\""+ImgUrl+"\" style=\"min-width: 300px;\"></div>");
				str.append("<div style=\"float: left; margin-left: 100px; padding-top: 45px; width: 500px;\">");				
				str.append("<div class=\"itemInfo\"><lable class=\"intag\">用户：</lable>"+user.getName()+"</div>");
				str.append("<div class=\"itemInfo\"><lable class=\"intag\">年龄：</lable>"+user.getAge()+"</div>");
				str.append("<div class=\"itemInfo\"><lable class=\"intag\">性别：</lable>"+sex+"</div>");
				str.append("<div class=\"itemInfo\"><lable class=\"intag\">职业：</lable>"+user.getOccupation()+"</div>");
				str.append("</div>");
				str.append("</div>");
				str.append("<div class=\"women-product\">");
				str.append("<div class=\"products\">");
				str.append("<h5 class=\"latest-product\">该用户还用过以下药物</h5>");
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
	

}
