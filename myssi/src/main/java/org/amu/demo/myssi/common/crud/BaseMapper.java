package org.amu.demo.myssi.common.crud;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

/**
 * 本来打算使用泛型。但其它它只是个信息收集的类。真正的功能实现在myBatis里面。所以其实泛型在这里没有意义。
 * 返回的时候不能识别正确的泛型。
 * 
 * 所有查询的东西都放在子类中。Base只负责基本的增删改。然后定义一些查询功能。但需要在子接口中加上调用@SelectProvider的注释
 * 
 * MyBatis CRUD基接口
 * @author amu
 * @param <T> 处理的POJO对象
 */
public interface BaseMapper<T> {

	public abstract T get(T obj);

	public abstract List<T> findBy(T obj, List<SearchFilter> searchFilterList, int pageNumber, int limit);
	
	@SelectProvider(type = CRUDTemplate.class,method = "count")
	public int count(Object obj, List<SearchFilter> searchFilterList);
	
	/**
	 * Insert语句从CUDTemplate类中生成
	 * @param obj
	 */
	@InsertProvider(type = CRUDTemplate.class, method = "insert")
	@Options(keyColumn="id", keyProperty="id", useGeneratedKeys=false)
//	@SelectKey(before=true, resultType=Long.class, keyProperty="id", statement={" select acct_seq.nextval from dual "})
	public void insert(Object obj);
	
	/**
	 * Update语句从CUDTemplate类中生成
	 * @param obj
	 */
	@UpdateProvider(type = CRUDTemplate.class, method = "update")
	public void update(Object obj);
	
	/**
	 * Delete语句从CUDTemplate类中生成
	 * @param obj
	 */
	@DeleteProvider(type = CRUDTemplate.class, method = "delete")
	public void delete(Object obj);
	
	public Long getKey();
}
