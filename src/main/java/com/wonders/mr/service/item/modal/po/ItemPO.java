package com.wonders.mr.service.item.modal.po;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 药品PO
 * 
 * @author xh
 * 
 */
@Entity
@Table(name="item")
public class ItemPO {

	private Long itemId;
	private String itemName;
	private Float price;
	private String category;// 药品类别
	private String tagIdList;// 存放药品所属标签的id，多个id用逗号隔开
	private String symptomDesc;// 使用症状描述
	private String company;// 厂家
	private String component;// 药品成分
	private String imgUrl;// 药品图片

	public ItemPO() {
	}

	public ItemPO(String itemName, Float price, String category,
			String tagIdList, String symptomDesc, String company,
			String component, String imgUrl) {
		this.itemName = itemName;
		this.price = price;
		this.category = category;
		this.tagIdList = tagIdList;
		this.symptomDesc = symptomDesc;
		this.company = company;
		this.component = component;
		this.imgUrl = imgUrl;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "item_id", unique = true, nullable = false)
	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	@Column(name = "item_name")
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Column(name = "price")
	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@Column(name = "category")
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Column(name = "tag_id_list")
	public String getTagIdList() {
		return tagIdList;
	}

	public void setTagIdList(String tagIdList) {
		this.tagIdList = tagIdList;
	}

	@Column(name = "symptom_desc")
	public String getsymptomDesc() {
		return symptomDesc;
	}

	public void setsymptomDesc(String symptomDesc) {
		this.symptomDesc = symptomDesc;
	}

	@Column(name = "company")
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Column(name = "component")
	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	@Column(name = "img_url")
	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
}
