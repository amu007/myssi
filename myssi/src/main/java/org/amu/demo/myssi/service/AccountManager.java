package org.amu.demo.myssi.service;

import org.amu.demo.myssi.common.crud.BaseManager;
import org.amu.demo.myssi.common.crud.BaseMapper;
import org.amu.demo.myssi.dao.UserMapper;
import org.amu.demo.myssi.entity.Department;
import org.amu.demo.myssi.entity.User;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 帐号管理类.
 * 
 * 实现领域对象用户及其相关实体的所有业务管理函数.
 * 使用Spring annotation定义事务管理.
 * 
 * @author amu
 */
//Spring Service Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional(readOnly = true)
public class AccountManager extends BaseManager<User> {

	@Autowired
	private UserMapper userMapper;

	@Override
	public BaseMapper<User> getBaseMapper() {
		return userMapper;
	}

	@Override
	public User getEntity() {
		return new User();
	}
	
	@Transactional(readOnly = true)
	public Department getDepartmentDetail(Long id) {
		Validate.notNull(id, "id参数为空");
		Department d = new Department();
		d.setId(id);
		return userMapper.getDepartmentDetail(d);
	}
}
