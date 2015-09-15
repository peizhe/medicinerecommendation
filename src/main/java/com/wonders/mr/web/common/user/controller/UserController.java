package com.wonders.mr.web.common.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wonders.bud.framework.common.util.RestMsg;
import com.wonders.mr.service.user.modal.UserPO;
import com.wonders.mr.service.user.service.UserService;

@Controller
@RequestMapping("api/common/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public RestMsg<UserPO> login(HttpServletRequest request,
			HttpServletResponse response) {
		RestMsg<UserPO> rm = new RestMsg<UserPO>();
		try {
			String userName = request.getParameter("userName");
			String passWord = request.getParameter("password");
			if (userName != null && passWord != null) {
				UserPO user = userService.login(userName, passWord);
				if (user != null) {
					rm.setResult(user);
					rm.setMsg("success");
					HttpSession session = request.getSession();
					session.setAttribute("userName", userName);
					session.setAttribute("loginName", user.getLoginName());
				} else {
					rm.setMsg("error");
				}
			}
		} catch (Exception e) {
			rm.setMsg("error");
			e.printStackTrace();
		}
		return rm;
	}

	/**
	 * 验证用户是否登录
	 * 
	 * @param request
	 * @return 登录返回 用户名 + ! + 登录名 ,如:张三!zhangsan，未登录返回no
	 */
	@RequestMapping(value = "/isLogin", method = RequestMethod.POST)
	@ResponseBody
	public RestMsg<String> isLogin(HttpServletRequest request) {

		RestMsg<String> restMsg = new RestMsg<String>();
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("userName");
		String loginName = (String) session.getAttribute("loginName");
		if (StringUtils.isEmpty(userName)) {
			restMsg.setMsg("no");
		} else {
			restMsg.setMsg(userName + "!" + loginName);
		}
		return restMsg;
	}
}
