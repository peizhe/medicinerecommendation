package com.wonders.mr.service.user.service;

import java.util.List;

import com.wonders.mr.service.user.modal.UserPO;

/**
 * 用户service接口定义
 * 
 * @author xh
 * 
 */
public interface UserService {

	/**
	 * 新增用户
	 * 
	 * @param userPO
	 *            用户对象
	 * @return 当前对象
	 */
	public void save(UserPO userPO);

	/**
	 * 更新对象
	 * 
	 * @param userPO
	 *            待更新对象
	 * @return
	 */
	public void update(UserPO userPO);

	/**
	 * 删除对象
	 * 
	 * @param id
	 *            待删除对象id
	 */
	public void delete(Long id);

	/**
	 * 根据id查找对象
	 * 
	 * @param id
	 *            对象id
	 * @return 对应的对象
	 */
	public UserPO findById(Long id);

	/**
	 * 根据登陆名查找
	 * 
	 * @param loginName
	 * @return
	 */
	public UserPO findByLoginName(String loginName);

	/**
	 * 查找所有对象
	 * 
	 * @return
	 */
	public List<UserPO> findAll();
	
	/**
	 * 根据登录名判断登陆密码是否正确
	 * 
	 * @param loginName
	 * @param pwd
	 * @return
	 */
	public int login(String loginName, String pwd);

}
