package com.wonders.mr.service.recitemtagsim.modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 物品相似度模块PO
 * 
 * @author lixiang
 *
 */
@Entity
@Table(name = "rec_item_tag_sim")
public class RecItemTagSimPO {
	
	private Long id;
	private Long itemId;
	private String itemIdList;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "item_id")
	public Long getItemId() {
		return itemId;
	}
	
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	
	@Column(name = "item_id_list")
	public String getItemIdList() {
		return itemIdList;
	}
	
	public void setItemIdList(String itemIdList) {
		this.itemIdList = itemIdList;
	}
	
	

}
