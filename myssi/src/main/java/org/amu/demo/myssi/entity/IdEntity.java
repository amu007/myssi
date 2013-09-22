package org.amu.demo.myssi.entity;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * 统一定义id的entity基类.
 * 
 * @author amu
 */
public abstract class IdEntity {

	protected Long id;

	@Id
	@Column
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
