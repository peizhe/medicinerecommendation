package com.wonders.mr.web.pages.index.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wonders.bud.framework.common.util.RestMsg;
import com.wonders.mr.service.item.modal.po.ItemPO;
import com.wonders.mr.service.item.modal.po.TagPO;
import com.wonders.mr.service.item.service.RecTableIcfService;
import com.wonders.mr.service.item.service.RecTableUcfService;
import com.wonders.mr.service.item.service.TagService;
import com.wonders.mr.service.recusersim.service.RecUserSimService;
import com.wonders.mr.service.user.modal.UserPO;

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
	
	/**
	 * 获取tag标签
	 * @param request
	 * @return 
	 */
	@RequestMapping(value = "/getTagInfo", method = RequestMethod.POST)
	@ResponseBody
	public RestMsg<List<Map<String, Object>>> getTagInfo(HttpServletRequest request) {
		RestMsg<List<Map<String, Object>>> rm = new RestMsg<List<Map<String, Object>>>();
		long[] ids = {1,2,3,4,5,6,7,8,9,10};		
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
					for(int i=0;i<4;i++){
						ItemPO item=items.get(i);
						String link="single.html";
						if(i<3){
							str.append("<div class=\"col-md-4 chain-grid\">");
						}
						else {
							str.append("<div class=\"col-md-4 chain-grid grid-top-chain\">");
						}
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
						str.append("<a class=\"now-get get-cart\" href=\"#\">加入购物车</a> ");
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
					for(int i=0;i<4;i++){
						ItemPO item=items.get(i);
						String link="single.html";
						if(i<3){
							str.append("<div class=\"col-md-4 chain-grid\">");
						}
						else {
							str.append("<div class=\"col-md-4 chain-grid grid-top-chain\">");
						}
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
						str.append("<a class=\"now-get get-cart\" href=\"#\">加入购物车</a> ");
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
	
	@RequestMapping(value = "/getRecUserSim", method = RequestMethod.GET)
	@ResponseBody
	public RestMsg<String> getRecUserSim(HttpServletRequest request){
		RestMsg<String> rm=new RestMsg<>();
		String htmlstr=new String();
		try {
			HttpSession session=request.getSession();
			Object userId=session.getAttribute("userId");
			if(userId!=null){
				List<UserPO> simUsers=recUserSimService.findByUserId(Long.parseLong(userId.toString()));
				if(simUsers!=null&&simUsers.size()>0){
					
					StringBuilder str=new StringBuilder();
					int limt=4;
					for(int i=0;i<limt;i++){
						UserPO friend=simUsers.get(i);
						String src="images/user.jpg";
						String link="single.html";
						if(i<3){
							str.append("<div class=\"col-md-4 chain-grid\">");
						}
						else {
							str.append("<div class=\"col-md-4 chain-grid grid-top-chain\">");
						}
						str.append("<a href=\""+link+"\"><img class=\"img-responsive chain\" src=\""+src+"\" alt=\" \" /></a>");
						str.append("<span class=\"star\"> </span>");
						str.append("<div class=\"grid-chain-bottom\">");
						str.append("<h6><a href=\""+link+"\">"+friend.getName()+"</a></h6>");
						str.append("<div class=\"star-price\">");
						str.append("<div class=\"clearfix\"> </div>");
						str.append("</div>");
						str.append("</div>");
						str.append("</div>");		
					}
					htmlstr=str.toString();
					rm.setMsg("success");
					rm.setResult(htmlstr);
				}
			}
		} catch (Exception e) {
			rm.setMsg("error");
			e.printStackTrace();
			log.error(e.getLocalizedMessage());
		}
		return rm;
	}

}
