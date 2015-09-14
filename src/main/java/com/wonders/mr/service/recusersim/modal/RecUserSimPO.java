package com.wonders.mr.service.recusersim.modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 找病友（相似用户推荐）PO
 * @author lixiang
 *
 */
@Entity
@Table(name = "rec_user_sim")
public class RecUserSimPO {
	
	private Long id;
	private Long userId;
	private String userIdList;
	
	public RecUserSimPO(){		
	}
	
	public RecUserSimPO(Long userId, String userIdList){
		this.userId = userId;
		this.userIdList = userIdList;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "user_id")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "user_id_list")
	public String getUserIdList() {
		return userIdList;
	}

	public void setUserIdList(String userIdList) {
		this.userIdList = userIdList;
	}
    		
}




