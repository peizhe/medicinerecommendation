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
		
		String occupation="未知";
		switch (number) {
		case "0":
			occupation="其他";
			break;
		case "1":
			occupation="学者/教育家";
			break;

		case "2":
			occupation="艺术家";
			break;
		case "3":
			occupation="文秘/管理";		
			break;
		case "4":
			occupation="大学生";
			break;
		case "5":
			occupation="客户服务";
			break;
		case "6":
			occupation="医生/医疗保健";
			break;
		case "7":
			occupation="行政/管理";
			break;
		case "8":
			occupation="农民";
			break;
		case "9":
			occupation="家庭主妇";
			break;
		case "10":
			occupation="中小学学生";
			break;
		case "11":
			occupation="律师";
			break;
		case "12":
			occupation="程序员";
			break;
		case "13":
			occupation="退休";
			break;
		case "14":
			occupation="销售/市场营销";
			break;
		case "15":
			occupation="科学家";
			break;
		case "16":
			occupation="个体经营";
			break;
		case "17":
			occupation="技术员/工程师";
			break;
		case "18":
			occupation="商人/工匠";
			break;
		case "19":
			occupation="失业";
			break;
		case "20":
			occupation="作家";
			break;
		default:
			break;
		}
		return occupation;
	}

}
