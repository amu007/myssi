package org.amu.demo.myssi.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Table;


import com.google.common.collect.Lists;

/**
 * 部门.
 * 
 * @author amu
 */
@Table(name = "acct_department")
public class Department extends IdEntity {

	private String name;
	private User manager;
	private List<User> userList = Lists.newArrayList();

	@Column
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
}
