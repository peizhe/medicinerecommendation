package com.wonders.mr.service.item.modal.po;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rec_table_icf")
public class RecTableIcfPO {
	private Long id;
	private Long userId;
	private String itemIdList;

	public RecTableIcfPO() {
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

	@Column(name = "user_id")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "item_id_list")
	public String getItemIdList() {
		return itemIdList;
	}

	public void setItemIdList(String itemIdList) {
		this.itemIdList = itemIdList;
	}

}
