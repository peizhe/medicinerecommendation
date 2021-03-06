package com.wonders.mr.web.pages.index.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wonders.bud.framework.common.util.RestMsg;
import com.wonders.mr.service.action.modal.ActionPO;
import com.wonders.mr.service.action.service.ActionService;
import com.wonders.mr.service.item.modal.po.ItemPO;
import com.wonders.mr.service.item.modal.po.TagPO;
import com.wonders.mr.service.item.service.ItemService;
import com.wonders.mr.service.item.service.RecTableIcfService;
import com.wonders.mr.service.item.service.RecTableUcfService;
import com.wonders.mr.service.item.service.TagService;
import com.wonders.mr.service.recusersim.service.RecUserSimService;
import com.wonders.mr.service.user.modal.UserPO;
import com.wonders.mr.web.pages.userInfo.controller.UserInfoController;

@Controller
@RequestMapping("pages/index/controller")
public class IndexTagController {
	protected Logger log = Logger.getLogger(IndexTagController.class); 
	@Autowired
	private RecTableUcfService recTableUcfService;
	@Autowired
	private RecUserSimService recUserSimService;
	@Resource
	private TagService tagService;
	@Resource
	private RecTableIcfService recTableIcfService;
	@Resource
	private ItemService itemService;
	@Resource
	private ActionService actionService;
	@Autowired
	private UserInfoController userInfoController;
	/**
	 * 获取tag标签分类
	 * @param request
	 * @return RestMsg<List<Map<String, Object>>>
	 */
	@RequestMapping(value = "/getTagInfo", method = RequestMethod.POST)
	@ResponseBody
	public RestMsg<List<Map<String, Object>>> getTagInfo(HttpServletRequest request) {
		RestMsg<List<Map<String, Object>>> rm = new RestMsg<List<Map<String, Object>>>();
		long[] ids = {2,3,4,5,6,7,8,9,10,11};		
		try {
			List<TagPO> tagPOs = tagService.findSpecial(ids);
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
	
	/**
	 * 获得搜索框搜索到的药品
	 * @param request
	 * @return RestMsg<List<Map<String, Object>>>
	 */
	@RequestMapping(value = "/getSearchInfo", method = RequestMethod.POST)
	@ResponseBody
	public RestMsg<List<Map<String, Object>>> getSearchInfo(HttpServletRequest request) {
		RestMsg<List<Map<String, Object>>> rm = new RestMsg<List<Map<String, Object>>>();
		String content = request.getParameter("content");
		try {
			List<ItemPO> itemPOs = itemService.findByInput(content);
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
			return rm;		}
		return rm;
	}
	
	@RequestMapping(value = "/getRecTableUcf", method = RequestMethod.GET)
	@ResponseBody
	public RestMsg<String> getRecTableUcf(HttpServletRequest request){
		RestMsg<String> rm=new RestMsg<>();
		String htmlstr=new String();
		try {
			HttpSession session=request.getSession();
			Object userId=session.getAttribute("userId");
			if(userId!=null){
				List<ItemPO> items=recTableUcfService.findByUserId(Long.parseLong(userId.toString()));
				if(items!=null&&items.size()>0){					
					StringBuilder str=new StringBuilder();
					for(int i=0;i<3;i++){
						ItemPO item=items.get(i);
						String link="single2.html?itemId="+item.getItemId();
						str.append("<div class=\"col-md-4 chain-grid grid-top-chain\" style=\"width:30%;margin-right:15px;margin-bottom:10px\">");
						str.append("<a href=\""+link+"\"><img class=\"img-responsive chain\" src=\""+item.getImgUrl()+"\" alt=\" \" /></a>");
						str.append("<span class=\"star\"> </span>");
						str.append("<div class=\"grid-chain-bottom\">");
						str.append("<h6><a href=\""+link+"\">"+item.getItemName()+"</a></h6>");
						str.append("<div class=\"star-price\">");
						str.append("<div class=\"dolor-grid\"> ");
						str.append("<span class=\"actual\">￥"+item.getPrice()+"</span>");
						str.append("<span class=\"reducedfrom\">"
								+ "</span>");
						str.append("<span class=\"rating\">");
						str.append("<input type=\"radio\" class=\"rating-input\" id=\"rating-input-1-5\" name=\"rating-input-1\">");
						str.append("<label for=\"rating-input-1-5\" class=\"rating-star1\"> </label>");
						str.append("<input type=\"radio\" class=\"rating-input\" id=\"rating-input-1-4\" name=\"rating-input-1\">");
						str.append("<label for=\"rating-input-1-4\" class=\"rating-star1\"> </label>");
						str.append(" <input type=\"radio\" class=\"rating-input\" id=\"rating-input-1-3\" name=\"rating-input-1\">");
						str.append("<label for=\"rating-input-1-3\" class=\"rating-star\"> </label>");
						str.append("<input type=\"radio\" class=\"rating-input\" id=\"rating-input-1-2\" name=\"rating-input-1\">");
						str.append("<label for=\"rating-input-1-2\" class=\"rating-star\"> </label>");
						str.append("<input type=\"radio\" class=\"rating-input\" id=\"rating-input-1-1\" name=\"rating-input-1\">");
						str.append("<label for=\"rating-input-1-1\" class=\"rating-star\"> </label>");
						str.append("</span>");
						str.append("</div>");
						str.append("<a class=\"now-get get-cart\" onclick=\"addToShoppingCart("+item.getItemId()+")\">购买</a> ");
						str.append("<div class=\"clearfix\"> </div>");
						str.append("</div>");
						str.append("</div>");
						str.append("</div>");
					}
					htmlstr=str.toString();
				}
			}
	/*		else {
				rm.setMsg("您还没登录");
			}*/
			rm.setResult(htmlstr);
			rm.setMsg("success");
		} catch (Exception e) {
			rm.setMsg("error");
			e.printStackTrace();
			log.error(e.getLocalizedMessage());
		}
		return rm;
	}
	
	@RequestMapping(value = "/getRecTableAllUcf", method = RequestMethod.GET)
	@ResponseBody
	public RestMsg<String> getRecTableAllUcf(HttpServletRequest request){
		RestMsg<String> rm=new RestMsg<>();
		String htmlstr=new String();
		try {
			HttpSession session=request.getSession();
			Object userId=session.getAttribute("userId");
			if(userId!=null){
				List<ItemPO> items=recTableUcfService.findByUserId(Long.parseLong(userId.toString()));
				if(items!=null&&items.size()>0){					
					StringBuilder str=new StringBuilder();
					for(int i=0;i<9;i++){
						ItemPO item=items.get(i);
						String link="single2.html?itemId="+item.getItemId();
						str.append("<div class=\"col-md-4 chain-grid grid-top-chain\"style=\"width:30%;margin-right:15px;margin-bottom:10px\">");
						str.append("<a href=\""+link+"\"><img class=\"img-responsive chain\" src=\""+item.getImgUrl()+"\" alt=\" \" /></a>");
						str.append("<span class=\"star\"> </span>");
						str.append("<div class=\"grid-chain-bottom\">");
						str.append("<h6><a href=\""+link+"\">"+item.getItemName()+"</a></h6>");
						str.append("<div class=\"star-price\">");
						str.append("<div class=\"dolor-grid\"> ");
						str.append("<span class=\"actual\">￥"+item.getPrice()+"</span>");
						str.append("<span class=\"reducedfrom\"></span>");
						str.append("<span class=\"rating\">");
						str.append("<input type=\"radio\" class=\"rating-input\" id=\"rating-input-1-5\" name=\"rating-input-1\">");
						str.append("<label for=\"rating-input-1-5\" class=\"rating-star1\"> </label>");
						str.append("<input type=\"radio\" class=\"rating-input\" id=\"rating-input-1-4\" name=\"rating-input-1\">");
						str.append("<label for=\"rating-input-1-4\" class=\"rating-star1\"> </label>");
						str.append(" <input type=\"radio\" class=\"rating-input\" id=\"rating-input-1-3\" name=\"rating-input-1\">");
						str.append("<label for=\"rating-input-1-3\" class=\"rating-star\"> </label>");
						str.append("<input type=\"radio\" class=\"rating-input\" id=\"rating-input-1-2\" name=\"rating-input-1\">");
						str.append("<label for=\"rating-input-1-2\" class=\"rating-star\"> </label>");
						str.append("<input type=\"radio\" class=\"rating-input\" id=\"rating-input-1-1\" name=\"rating-input-1\">");
						str.append("<label for=\"rating-input-1-1\" class=\"rating-star\"> </label>");
						str.append("</span>");
						str.append("</div>");
						str.append("<a class=\"now-get get-cart\" onclick=\"addToShoppingCart("+item.getItemId()+")\">购买</a> ");
						str.append("<div class=\"clearfix\"> </div>");
						str.append("</div>");
						str.append("</div>");
						str.append("</div>");
					}
					htmlstr=str.toString();
				}
			}
	/*		else {
				rm.setMsg("您还没登录");
			}*/
			rm.setResult(htmlstr);
			rm.setMsg("success");
		} catch (Exception e) {
			rm.setMsg("error");
			e.printStackTrace();
			log.error(e.getLocalizedMessage());
		}
		return rm;
	}

	
	@RequestMapping(value = "/getRecTableIcf", method = RequestMethod.GET)
	@ResponseBody
	public RestMsg<String> getRecTableIcf(HttpServletRequest request){
		RestMsg<String> rm=new RestMsg<>();
		String htmlstr=new String();
		try {
			HttpSession session=request.getSession();
			Object userId=session.getAttribute("userId");
			if(userId!=null){
				List<ItemPO> items=recTableIcfService.findByUserId(Long.parseLong(userId.toString()));
				if(items!=null&&items.size()>0){					
					StringBuilder str=new StringBuilder();
					for(int i=0;i<3;i++){
						ItemPO item=items.get(i);
						String link="single2.html?itemId="+item.getItemId();
						str.append("<div class=\"col-md-4 chain-grid grid-top-chain\" style=\"width:30%;margin-right:15px;margin-bottom:10px\">");
						str.append("<a href=\""+link+"\"><img class=\"img-responsive chain\" src=\""+item.getImgUrl()+"\" alt=\" \" /></a>");
						str.append("<span class=\"star\"> </span>");
						str.append("<div class=\"grid-chain-bottom\">");
						str.append("<h6><a href=\""+link+"\">"+item.getItemName()+"</a></h6>");
						str.append("<div class=\"star-price\">");
						str.append("<div class=\"dolor-grid\"> ");
						str.append("<span class=\"actual\">￥"+item.getPrice()+"</span>");
						str.append("<span class=\"reducedfrom\">￥100</span>");
						str.append("<span class=\"rating\">");
						str.append("<input type=\"radio\" class=\"rating-input\" id=\"rating-input-1-5\" name=\"rating-input-1\">");
						str.append("<label for=\"rating-input-1-5\" class=\"rating-star1\"> </label>");
						str.append("<input type=\"radio\" class=\"rating-input\" id=\"rating-input-1-4\" name=\"rating-input-1\">");
						str.append("<label for=\"rating-input-1-4\" class=\"rating-star1\"> </label>");
						str.append(" <input type=\"radio\" class=\"rating-input\" id=\"rating-input-1-3\" name=\"rating-input-1\">");
						str.append("<label for=\"rating-input-1-3\" class=\"rating-star\"> </label>");
						str.append("<input type=\"radio\" class=\"rating-input\" id=\"rating-input-1-2\" name=\"rating-input-1\">");
						str.append("<label for=\"rating-input-1-2\" class=\"rating-star\"> </label>");
						str.append("<input type=\"radio\" class=\"rating-input\" id=\"rating-input-1-1\" name=\"rating-input-1\">");
						str.append("<label for=\"rating-input-1-1\" class=\"rating-star\"> </label>");
						str.append("</span>");
						str.append("</div>");
						str.append("<a class=\"now-get get-cart\" onclick=\"addToShoppingCart("+item.getItemId()+")\">购买</a> ");
						str.append("<div class=\"clearfix\"> </div>");
						str.append("</div>");
						str.append("</div>");
						str.append("</div>");
					}
					htmlstr=str.toString();
				}
			}
	/*		else {
				rm.setMsg("您还没登录");
			}*/
			rm.setResult(htmlstr);
			rm.setMsg("success");
		} catch (Exception e) {
			rm.setMsg("error");
			e.printStackTrace();
			log.error(e.getLocalizedMessage());
		}
		return rm;
	}
	@RequestMapping(value = "/getRecTableAllIcf", method = RequestMethod.GET)
	@ResponseBody
	public RestMsg<String> getRecTableAllIcf(HttpServletRequest request){
		RestMsg<String> rm=new RestMsg<>();
		String htmlstr=new String();
		try {
			HttpSession session=request.getSession();
			Object userId=session.getAttribute("userId");
			if(userId!=null){
				List<ItemPO> items=recTableIcfService.findByUserId(Long.parseLong(userId.toString()));
				if(items!=null&&items.size()>0){					
					StringBuilder str=new StringBuilder();
					for(int i=0;i<9;i++){
						ItemPO item=items.get(i);
						String link="single2.html?itemId="+item.getItemId();
						str.append("<div class=\"col-md-4 chain-grid grid-top-chain\"style=\"width:30%;margin-right:15px;margin-bottom:10px\">");
						str.append("<a href=\""+link+"\"><img class=\"img-responsive chain\" src=\""+item.getImgUrl()+"\" alt=\" \" /></a>");
						str.append("<span class=\"star\"> </span>");
						str.append("<div class=\"grid-chain-bottom\">");
						str.append("<h6><a href=\""+link+"\">"+item.getItemName()+"</a></h6>");
						str.append("<div class=\"star-price\">");
						str.append("<div class=\"dolor-grid\"> ");
						str.append("<span class=\"actual\">￥"+item.getPrice()+"</span>");
						str.append("<span class=\"reducedfrom\">￥100</span>");
						str.append("<span class=\"rating\">");
						str.append("<input type=\"radio\" class=\"rating-input\" id=\"rating-input-1-5\" name=\"rating-input-1\">");
						str.append("<label for=\"rating-input-1-5\" class=\"rating-star1\"> </label>");
						str.append("<input type=\"radio\" class=\"rating-input\" id=\"rating-input-1-4\" name=\"rating-input-1\">");
						str.append("<label for=\"rating-input-1-4\" class=\"rating-star1\"> </label>");
						str.append(" <input type=\"radio\" class=\"rating-input\" id=\"rating-input-1-3\" name=\"rating-input-1\">");
						str.append("<label for=\"rating-input-1-3\" class=\"rating-star\"> </label>");
						str.append("<input type=\"radio\" class=\"rating-input\" id=\"rating-input-1-2\" name=\"rating-input-1\">");
						str.append("<label for=\"rating-input-1-2\" class=\"rating-star\"> </label>");
						str.append("<input type=\"radio\" class=\"rating-input\" id=\"rating-input-1-1\" name=\"rating-input-1\">");
						str.append("<label for=\"rating-input-1-1\" class=\"rating-star\"> </label>");
						str.append("</span>");
						str.append("</div>");
						str.append("<a class=\"now-get get-cart\" onclick=\"addToShoppingCart("+item.getItemId()+")\">购买</a> ");
						str.append("<div class=\"clearfix\"> </div>");
						str.append("</div>");
						str.append("</div>");
						str.append("</div>");
					}
					htmlstr=str.toString();
				}
			}
	/*		else {
				rm.setMsg("您还没登录");
			}*/
			rm.setResult(htmlstr);
			rm.setMsg("success");
		} catch (Exception e) {
			rm.setMsg("error");
			e.printStackTrace();
			log.error(e.getLocalizedMessage());
		}
		return rm;
	}
	
	@RequestMapping(value = "/getRecUserSim", method = RequestMethod.POST)
	@ResponseBody
	public RestMsg<String> getRecUserSim(HttpServletRequest request){
		RestMsg<String> rm=new RestMsg<>();
		List<UserPO> simUsers=new ArrayList<>();
		try {
			
			HttpSession session=request.getSession();
			Object userId=session.getAttribute("userId");
			String page=request.getParameter("page");		
			if(userId!=null&&page!=null){
				int currentPage=Integer.parseInt(page);
				simUsers=recUserSimService.findByUserId(Long.parseLong(userId.toString()));
				if(simUsers!=null&&simUsers.size()>0){
					
					StringBuilder str=new StringBuilder();
					int total=simUsers.size();
					int record=5;
					int totalPage=total%record==0?total/record:(total/record+1);
					int start=(currentPage-1)*record;
					int end=currentPage*record<total?currentPage*record:total;
					
					for(int i=start;i<end;i++){
						UserPO user=simUsers.get(i);
						String  link="userInfo.html?userId="+user.getId();
						String imgeUrl="images/user.jpg";
						str.append("<li><div class=\"user-info\">");
						str.append("<a href=\""+link+"\" class=\"user-avatar\" target=\"\"><img src=\""+imgeUrl+"\"></a>");
						str.append("<div class=\"info-details\">");
						str.append("<p>");
						str.append("<a href=\""+link+"\" title=\""+user.getLoginName()+"\" target=\"\" class=\"user-name textoverflow\">"+user.getLoginName()+"</a>");
						str.append("</p>");
						str.append("<p>");
						str.append("<span class=\"user-desc c_tx3\">职业："+userInfoController.getUserOccupation(user.getOccupation())+"</span>");
						str.append("</p></div>");
						str.append("<a class=\"close-button\" title=\"3天内不再显示此人\" uin=\"1716009482\" data-hottag=\"ISD.QZONEGIFT.QZONEINFOCENTER.CENTER-closeuin\" href=\"\">×</a>");
						str.append("</div><a class=\"button bgr2 c_tx_2\" href=\""+link+"\">");
						str.append("<i class=\"icon-cake\"></i><b class=\"c_tx2\">详情</b>");
						str.append("</a></li>");
					}
					
					rm.setMsg("success");
					rm.setCode(totalPage);
					rm.setResult(str.toString());
				}
			}
			else {
				
			}
		} catch (Exception e) {
			rm.setMsg("error");
			e.printStackTrace();
			log.error(e.getLocalizedMessage());
		}
		return rm;
	}
	/**
	 * 加入购物车
	 * @param request
	 * @param itemId
	 * @return
	 */
	//TODO
	@RequestMapping(value = "/addToShoppingCart/{itemId}", method = RequestMethod.POST)
	@ResponseBody
	public RestMsg<String> addToShoppingCart(HttpServletRequest request, @PathVariable Long itemId){
		RestMsg<String> rm=new RestMsg<>();
		try{
			HttpSession session=request.getSession();
			Object userId=session.getAttribute("userId");
			ActionPO actionpo = new ActionPO();
			actionpo.setItemId(itemId);
			actionpo.setUserId((Long) userId);
			actionService.saveAction(actionpo);
			rm.setMsg("success");
		} catch (Exception e) {
			rm.setMsg("error");
		}
		return rm;
	}
}
