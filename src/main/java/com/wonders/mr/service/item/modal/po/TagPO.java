package com.wonders.mr.service.item.modal.po;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * TagPO
 * @author xh
 *
 */
@Entity
@Table(name="tag")
public class TagPO {

	private Long tagId;
	private String symptom;
	
	public TagPO() {
	}

	public TagPO(Long tagId, String symptom) {
		this.tagId = tagId;
		this.symptom = symptom;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "tag_id", unique = true, nullable = false)
	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	@Column(name="symptom")
	public String getSymptom() {
		return symptom;
	}

	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}
}
