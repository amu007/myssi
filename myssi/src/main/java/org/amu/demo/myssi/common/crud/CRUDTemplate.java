package org.amu.demo.myssi.common.crud;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.DELETE_FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SelectBuilder;

/**
 * 主要的sql生成类
 * @author amu
 *
 */
public class CRUDTemplate{
	
	
	public String insert(Object obj) throws Exception {
		BEGIN();

		INSERT_INTO(EntityUtil.tablename(obj));
		
		EntityUtil.caculationColumnList(obj);
		VALUES(EntityUtil.returnInsertColumnsName(obj), EntityUtil.returnInsertColumnsDefine(obj));
		return SQL();
	}
	
	public String update(Object obj) throws Exception {
		String idname = EntityUtil.id(obj);
		
		BEGIN();
		
		UPDATE(EntityUtil.tablename(obj));
		EntityUtil.caculationColumnList(obj);
		SET(EntityUtil.returnWhereDefine(obj));
		WHERE(idname + "=#{" + idname + "}");
		
		return SQL();
	}
	
	public String delete(Object obj) throws Exception {
		String idname = EntityUtil.id(obj);
		
		BEGIN();
		
		DELETE_FROM(EntityUtil.tablename(obj));
		WHERE(idname + "=#{" + idname + "}");
		
		return SQL();
	}

	public String findAll(Object obj) throws Exception {
		EntityUtil.caculationColumnList(obj);
		SelectBuilder.BEGIN(); // Clears ThreadLocal variable
		SelectBuilder.SELECT(EntityUtil.returnSelectColumnsName(obj));
		SelectBuilder.FROM(EntityUtil.tablename(obj));
		String paramString = EntityUtil.returnWhereDefine(obj);
		if (StringUtils.isNotBlank(paramString)) {
			SelectBuilder.WHERE(paramString);
		}
		return SelectBuilder.SQL();
	}
	

	public String findBy(Object obj) throws Exception {
//		System.out.println(test);
		Map map = (Map)obj;
		Object entity = map.get("0");
		EntityUtil.caculationColumnList(entity);
		SelectBuilder.BEGIN(); // Clears ThreadLocal variable
		SelectBuilder.SELECT(EntityUtil.returnSelectColumnsName(entity));
		SelectBuilder.FROM(EntityUtil.tablename(entity));
		
		List<SearchFilter> searchFilterList = (List<SearchFilter>)map.get("1");
		String paramString = EntityUtil.returnWhereDefine(entity, searchFilterList);
		if (StringUtils.isNotBlank(paramString)) {
			SelectBuilder.WHERE(paramString);
		}
		String sql = SelectBuilder.SQL();
		
		Object start = map.get("2");
		Object pageSize = map.get("3");
		if (start != null && pageSize != null && !start.equals(0) && !pageSize.equals(0)) {
			int startInt = (Integer)start;
			int pageSizeInt = (Integer)pageSize;
			sql += " limit " +(startInt-1)*pageSizeInt + "," + pageSizeInt;
		}
		
		return sql;
	}
	
	public String count(Object obj) throws Exception {
		Map map = (Map)obj;
		Object entity = map.get("0");
		EntityUtil.caculationColumnList(entity);
		SelectBuilder.BEGIN(); // Clears ThreadLocal variable

		String idname = EntityUtil.id(entity);
		
		SelectBuilder.SELECT("count(" + idname + ")");
		SelectBuilder.FROM(EntityUtil.tablename(entity));
		
		List<SearchFilter> searchFilterList = (List<SearchFilter>)map.get("1");
		String paramString = EntityUtil.returnWhereDefine(entity, searchFilterList);
		if (StringUtils.isNotBlank(paramString)) {
			SelectBuilder.WHERE(paramString);
		}
		return SelectBuilder.SQL();
	}
	
	public String get(Object obj) throws Exception {
		EntityUtil.caculationColumnList(obj);
		String idname = EntityUtil.id(obj);
		
		SelectBuilder.BEGIN();
		SelectBuilder.SELECT(EntityUtil.returnSelectColumnsName(obj));
		
		SelectBuilder.FROM(EntityUtil.tablename(obj));
		SelectBuilder.WHERE(idname + "=#{" + idname + "}");
		return SelectBuilder.SQL();
	}
	
}

