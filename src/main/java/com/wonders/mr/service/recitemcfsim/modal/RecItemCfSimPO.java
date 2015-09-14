package com.wonders.mr.service.recitemcfsim.modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 用过该药的用户还用过po
 * 
 * @author lixiang
 *
 */
@Entity
@Table(name = "rec_item_cf_sim")
public class RecItemCfSimPO {
	
	private Long id;
	private Long itemId;
	private String itemIdList;
	
	public RecItemCfSimPO () {		
	}
	
	public RecItemCfSimPO (Long itemId, String itemIdList) {
		this.itemId = itemId;
		this.itemIdList = itemIdList;
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

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getItemIdList() {
		return itemIdList;
	}

	public void setItemIdList(String itemIdList) {
		this.itemIdList = itemIdList;
	}
}
