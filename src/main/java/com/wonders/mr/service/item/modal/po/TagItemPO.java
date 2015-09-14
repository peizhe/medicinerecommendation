package com.wonders.mr.service.item.modal.po;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tag_items")
public class TagItemPO {

	private Long id;
	private Long tagId;
	private String itemIdList;// 药品id，多个id之间用逗号隔开

	public TagItemPO() {
	}

	public TagItemPO(Long tagId, String itemIdList) {
		this.tagId = tagId;
		this.itemIdList = itemIdList;
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

	@Column(name = "tag_id")
	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	@Column(name = "item_id_list")
	public String getItemIdList() {
		return itemIdList;
	}

	public void setItemIdList(String itemIdList) {
		this.itemIdList = itemIdList;
	}
}
