package org.amu.demo.myssi.common.crud;

/**
 * Copyright (c) 2005-2012 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/**
 * 从request中怎能提取查询条件
 * @author amu
 */
public class Servlets {

	public static List<SearchFilter> getParametersStartingWith(ServletRequest request, String prefix) {
		Validate.notNull(request, "Request must not be null");
		Enumeration paramNames = request.getParameterNames();

		List<SearchFilter> searchFilterList = new ArrayList<SearchFilter>();
		if (prefix == null) {
			prefix = "";
		}
		while (paramNames != null && paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			
			if ("".equals(prefix) || paramName.startsWith(prefix)) {
				String unprefixed = paramName.substring(prefix.length());
				String[] values = request.getParameterValues(paramName);
				if (values == null || values.length == 0) {
					// Do nothing, no values found at all.
				} else {
					Object value = null;
					if (values.length > 1) {
						value = values;
					} else {
						value = values[0];
						if (value == null || value.equals("")) {
							continue;
						}
					}
					String[] names = StringUtils.split(paramName, "_");
					if (names.length != 3) {
						throw new IllegalArgumentException(paramName + " is not a valid search filter name");
					}
					SearchFilter filter = new SearchFilter(names[2], SearchFilter.Operator.valueOf(names[1]), value);
					searchFilterList.add(filter);
				}
			}
		}
		return searchFilterList;
	}


}

