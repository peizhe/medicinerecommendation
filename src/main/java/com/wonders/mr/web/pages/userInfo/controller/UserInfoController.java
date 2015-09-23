package com.wonders.mr.web.pages.userInfo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.impl.xb.xsdschema.TotalDigitsDocument.TotalDigits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wonders.bud.framework.common.page.Page;
import com.wonders.bud.framework.common.util.QueryParam;
import com.wonders.bud.framework.common.util.RestMsg;
import com.wonders.mr.service.action.modal.ActionPO;
import com.wonders.mr.service.action.service.ActionService;
import com.wonders.mr.service.item.modal.po.ItemPO;
import com.wonders.mr.service.item.service.ItemService;
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
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping(value = "/getAllItemsByUserId", method = RequestMethod.POST)
	@ResponseBody
	public RestMsg<List<Map<String, String>>> getAllItemsByUserId(HttpServletRequest request){
		RestMsg<List<Map<String, String>>> rm=new RestMsg<>();
		try {
			
			String userId=request.getParameter("userId");
			String cpage=request.getParameter("batch");
			if(userId!=null&&cpage!=null){
				
				int size=10;
				int start=(Integer.parseInt(cpage)-1)*size;
				Page<ActionPO> actions=actionService.findItems(Long.parseLong(userId), start, size);			
				List<ActionPO> actionPOs=actions.getResult();//获取items		
				int total=actions.getTotal()%size==0?actions.getTotal()/size:actions.getTotal()/size+1;
				List<ItemPO> items = new ArrayList<>();
				for (ActionPO actionPO : actionPOs) {// 遍历，查找所有药品
					ItemPO itemPO = itemService.findById(actionPO.getItemId());
					items.add(itemPO);
				}
				if(items!=null&&items.size()>0){					

					List<Map<String, String>> jsonList=new ArrayList<>();
					for(int i=0;i<items.size();i++){
						ItemPO item=items.get(i);
						Map<String, String>	map=new HashMap<>();
						map.put("id", item.getItemId().toString());
						map.put("image", item.getImgUrl());
						map.put("symptomDesc", item.getsymptomDesc());
						map.put("name", item.getItemName());
						map.put("price", item.getPrice().toString());
						jsonList.add(map);
						
					}	
					rm.setMsg("success");
					rm.setResult(jsonList);
					rm.setCode(total);
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
