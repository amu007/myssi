<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script src="${ctx}/static/aa.js" type="text/javascript"></script>
</head>
<body>

	<form method="POST" action="${ctx }/my/upload/upload" enctype="multipart/form-data">
		<input type="text" name="name"/>
		<input type="file" name="file"/>
		<input type="submit"/>
	</form>
				
</body>
</html>