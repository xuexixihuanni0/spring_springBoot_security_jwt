<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<%-- ${pageContext.request.contextPath}/login 其实是指向了springsecurity自己的登录页面，从而实现认证--%>
<form action="${pageContext.request.contextPath}/login" method="post">
    用户名：<input type="text" name="username"><br>
    密码：<input type="password" name="password">
    <input type="submit" value="登录">

</form>
</body>
</html>
