package com.wonders.mr.service.action.modal;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户用药PO
 * @author xh
 *
 */
@Entity
@Table(name = "action")
public class ActionPO {

	private Long id;
	private Long userId;
	private Long itemId;

	public ActionPO() {
		super();
	}

	public ActionPO(Long userId, Long itemId) {
		super();
		this.userId = userId;
		this.itemId = itemId;
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

	@Column(name = "item_id")
	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

}
