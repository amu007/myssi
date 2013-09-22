<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script src="${ctx}/static/aa.js" type="text/javascript"></script>
</head>
<body>
	<form:form id="inputForm" modelAttribute="user"
		action="${ctx }/my/demo/save" method="post">
		<input type="hidden" name="id" value="${user.id}" />
		<div>
			<label for="loginName" class="field">登录名:</label> 
			<input type="text" id="loginName" name="loginName" size="40" value="${user.loginName}" class="required" />
		</div>
		<div>
			<label for="name" class="field">姓名:</label> 
			<input type="text" id="name" name="name" size="40" value="${user.name}" class="required" />
		</div>

		<div>
			<label for="age" class="field">年龄:</label> 
			<input type="text" id="age" name="age" size="40" value="${user.age}" class="required" />
		</div>
		<input id="submit" class="button" type="submit" value="提交" />&nbsp;	
</form:form>

</body>
</html>