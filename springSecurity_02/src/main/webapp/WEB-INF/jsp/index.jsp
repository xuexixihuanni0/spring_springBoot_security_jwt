<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<html>
<head>
    <title>首页</title>
</head>
<body>
具有以下功能：<br>
<security:authorize access="hasRole('ROLE_ADD')">
<a href="${pageContext.request.contextPath}/user/add" style="font-size: 20px;">添加</a><br>
</security:authorize>
<security:authorize access="hasRole('ROLE_LIST')">
<a href="${pageContext.request.contextPath}/user/list" style="font-size: 20px;">查询</a><br>
</security:authorize>
<security:authorize access="hasRole('ROLE_UPDATE')">
<a href="${pageContext.request.contextPath}/user/update" style="font-size: 20px;">修改</a><br>
</security:authorize>
<security:authorize access="hasRole('ROLE_DELETE')">
<a href="${pageContext.request.contextPath}/user/delete" style="font-size: 20px;">删除</a><br>
</security:authorize>
</body>
</html>
