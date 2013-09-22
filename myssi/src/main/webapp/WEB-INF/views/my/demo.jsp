<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="${ctx }/js/jquery.js"></script>
<script type="text/javascript" src="${ctx }/js/table.js"></script>
</head>
<body>
	<form id="mainForm" action="${ctx }/my/demo" method="get">
	<input type="hidden" name="pageNo" id="pageNo" value="${page.pageNo}"/>
	<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
	<input type="hidden" name="page.order" id="order" value="${page.order}"/>
	姓名: <input type="text" name="search_LIKE_name" value="${param['search_LIKE_name']}" size="9"/>
	登录名: <input type="text" name="search_LIKE_loginName" value="${param['search_LIKE_loginName']}" size="9"/>
	年龄>: <input type="text" name="search_GTE_age" value="${param['search_GTE_age']}" size="9"/>
	<: <input type="text" name="search_LTE_age" value="${param['search_LTE_age']}" size="9"/>
			<input type="button" value="搜索" onclick="search();"/>
	<table id="contentTable" border=1>
		<tr><th>id</th><th>登录名</th><th>用户名</th><th>邮箱</th><th>年龄</th><th>权限组<th>操作</th></tr>
		<c:forEach items="${page.result}" var="user">
			<tr>
				<td>${user.id}</td>
				<td>${user.loginName}</td>
				<td>${user.name}</td>
				<td>${user.email}</td>
				<td>${user.age}</td>
				<td>
	    			<a href="${ctx }/my/demo/input?id=${user.id}" id="editLink-${user.name}">修改</a> <a href="${ctx }/my/demo/delete/${user.id}">删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	第${page.pageNo}页, 共${page.totalPages}页
			<a href="javascript:jumpPage(1)">首页</a>
			<s:if test="page.hasPre"><a href="javascript:jumpPage(${page.prePage})">上一页</a></s:if>
			<s:if test="page.hasNext"><a href="javascript:jumpPage(${page.nextPage})">下一页</a></s:if>
			<a href="javascript:jumpPage(${page.totalPages})">末页</a>

	<a href="${ctx }/my/demo/input" >新增</a> 
	</form>
				
</body>
</html>