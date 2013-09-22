package org.amu.demo.myssi.common.crud;

import java.util.List;

import org.amu.demo.myssi.entity.IdEntity;
import org.springframework.transaction.annotation.Transactional;


/**
 * 基于service层的crud模版
 * @author amu 
 * @param <T>
 */
public abstract class BaseManager<T extends IdEntity> {
	
	private static final int USE_DEFAULT_PAGE_SIZE = -1;
	
	@Transactional(readOnly = false)
	public void delete(Long id) {
		T t = this.getEntity();
		t.setId(id);
		getBaseMapper().delete(t);
	}

	@Transactional(readOnly = false)
	public void save(T t) {
		getBaseMapper().insert(t);
	}
	
	@Transactional(readOnly = false)
	public void update(T t) {
		getBaseMapper().update(t);
	}
	
	@Transactional(readOnly = true)
	public List<T> findBy(List<SearchFilter> searchFilterList, int pageNumber, int limit) {
		return getBaseMapper().findBy(getEntity(), searchFilterList, pageNumber, limit);
	}
	
	@Transactional(readOnly = true)
	public List<T> findBy(List<SearchFilter> searchFilterList) {
		return getBaseMapper().findBy(getEntity(), searchFilterList, 0, 0);
	}
	
	@Transactional(readOnly = true)
	public T get(Long id) {
		T t = getEntity();
		t.setId(id);
		return getBaseMapper().get(t);
	}
	
	public Page<T> findPage(List<SearchFilter> searchFilterList, int pageNumber, int limit) {
		int totalCount = this.getBaseMapper().count(getEntity(), searchFilterList);
		Page<T> page = new Page<T>();
		if (USE_DEFAULT_PAGE_SIZE == limit) {
			limit = page.getPageSize();
		}
		List<T> result = findBy(searchFilterList, pageNumber, limit);
		page.setPageNo(pageNumber);
		page.setTotalCount(totalCount);
		page.setResult(result);
		return page;
	}

	public Page<T> findPage(List<SearchFilter> searchFilterList, int pageNumber){ 
		return findPage(searchFilterList, pageNumber, USE_DEFAULT_PAGE_SIZE);
	}
	
	public abstract T getEntity();
	public abstract BaseMapper<T> getBaseMapper();
	
}
