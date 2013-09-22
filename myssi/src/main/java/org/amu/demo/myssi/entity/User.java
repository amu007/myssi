package org.amu.demo.myssi.entity;

import javax.persistence.Column;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 用户.
 * 
 * @author amu
 */
@Table(name = "acct_user")
public class User extends IdEntity {

	private static final long serialVersionUID = -2007748623349184442L;

	private String loginName;
	private String password;
	private String name;
	private String email;
	private Department department;
	private Integer age;

	@Column(name="department_id")
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Column(name="login_name")
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Column
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}