package com.wonders.mr.web.common.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wonders.bud.framework.common.util.RestMsg;
import com.wonders.mr.service.user.service.UserService;

@Controller
@RequestMapping("api/common/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public RestMsg<String> login(HttpServletRequest request,HttpServletResponse response){
		RestMsg<String> rm=new RestMsg<>();
		try {
			String userName=request.getParameter("userName");
			String passWord=request.getParameter("password");
			if(userName!=null&&passWord!=null){
				int isExist=1;
				if(isExist==1){
					rm.setResult("success");
					HttpSession session = request.getSession(); 
					session.setAttribute("userName", userName);
				}
				else{
					rm.setResult("error");
				}
			}
			
		} catch (Exception e) {
			rm.setMsg("error");
			e.printStackTrace();
		}
		return rm;
	}
}
