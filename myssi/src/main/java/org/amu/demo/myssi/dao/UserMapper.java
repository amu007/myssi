package org.amu.demo.myssi.dao;

import java.util.List;

import org.amu.demo.myssi.common.crud.BaseMapper;
import org.amu.demo.myssi.common.crud.CRUDTemplate;
import org.amu.demo.myssi.common.crud.SearchFilter;
import org.amu.demo.myssi.entity.Department;
import org.amu.demo.myssi.entity.User;
import org.apache.ibatis.annotations.SelectProvider;


/**
 * 只需要继承BaseMapper,就可以直接实现根据主键的CRUD操作
 * 自定义的sql就直接写到UserMapper.xml中
 * @author amu
 *
 */
public interface UserMapper extends BaseMapper<User> {
	
	@SelectProvider(type = CRUDTemplate.class, method = "get")
	public Department getDepartmentDetail(Department d);
	
	@SelectProvider(type = CRUDTemplate.class, method = "get")
	@Override
	public User get(User obj);

	@SelectProvider(type = CRUDTemplate.class, method = "findBy")
	@Override
	public List<User> findBy(User obj, List<SearchFilter> searchFilterList, int pageNumber, int limit);

//	@SelectProvider(type = CRUDTemplate.class, method = "findAll")
//	public List<User> getAll(User obj);
	

}