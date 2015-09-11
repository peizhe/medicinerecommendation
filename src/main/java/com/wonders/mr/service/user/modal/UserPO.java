package com.wonders.mr.service.user.modal;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户PO
 * 
 * @author xh
 * 
 */
@Entity
@Table(name = "user")
public class UserPO {

	private Long id;
	private String name;
	private String loginName;
	private Integer age;
	private String gender;// 用户性别
	private Integer deleteFlag;

	public UserPO() {
	}

	public UserPO(String name, String loginName, Integer age, String gender,
			Integer deleteFlag) {
		this.name = name;
		this.loginName = loginName;
		this.age = age;
		this.gender = gender;
		this.deleteFlag = deleteFlag;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "login_name")
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "age")
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name = "gender")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "delete_flag")
	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

}
