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
			String page=request.getParameter("batch");
			if(userId!=null&&page!=null){
				
				int batch=Integer.parseInt(page);
				List<ItemPO> items=actionService.findByUserId(Long.parseLong(userId));
				
				if(items!=null&&items.size()>0){
					
					int count=10;
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
						str.append("<span class=\"s-price\"><em class=\"s-price-sign\">¥</em><em class=\"s-value\">"+item.getPrice()+"</em></span> <span class=\"s-history-price\"><em class=\"s-price-sign\">¥</em><em class=\"s-value\">119.00</em></span>");	
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
				str.append("<div>");
				str.append("<div style=\"float: left; width: 300px\"><img alt=\"\" src=\""+ImgUrl+"\" style=\"min-width: 300px;\"></div>");
				str.append("<div style=\"float: left; margin-left: 100px; padding-top: 45px; width: 500px;\">");				
				str.append("<div class=\"itemInfo\"><lable class=\"intag\">用户：</lable>"+user.getLoginName()+"</div>");
				str.append("<div class=\"itemInfo\"><lable class=\"intag\">年龄：</lable>"+user.getAge()+"</div>");
				str.append("<div class=\"itemInfo\"><lable class=\"intag\">性别：</lable>"+sex+"</div>");
				str.append("<div class=\"itemInfo\"><lable class=\"intag\">职业：</lable>"+getUserOccupation(user.getOccupation())+"</div>");
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
	
	public String getUserOccupation(String number){
		
		String occupation="unknown";
		switch (number) {
		case "0":
			occupation="other";
			break;
		case "1":
			occupation="academic/educator";
			break;

		case "2":
			occupation="artist";
			break;
		case "3":
			occupation="clerical/admin";		
			break;
		case "4":
			occupation="college/grad student";
			break;
		case "5":
			occupation="customer service";
			break;
		case "6":
			occupation="doctor/health care";
			break;
		case "7":
			occupation="executive/managerial";
			break;
		case "8":
			occupation="farmer";
			break;
		case "9":
			occupation="homemaker";
			break;
		case "10":
			occupation="K-12 student";
			break;
		case "11":
			occupation="lawyer";
			break;
		case "12":
			occupation="programmer";
			break;
		case "13":
			occupation="retired";
			break;
		case "14":
			occupation="sales/marketing";
			break;
		case "15":
			occupation="scientist";
			break;
		case "16":
			occupation="self-employed";
			break;
		case "17":
			occupation="technician/engineer";
			break;
		case "18":
			occupation="tradesman/craftsman";
			break;
		case "19":
			occupation="unemployed";
			break;
		case "20":
			occupation="writer";
			break;
		default:
			break;
		}
		return occupation;
	}

}
