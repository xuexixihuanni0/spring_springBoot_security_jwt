<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <title>登录</title>
</head>
<body>
<c:if test="${not empty param.error}">
    <font color="red">用户名或密码错误</font>
</c:if>
<%-- ${pageContext.request.contextPath}/login 其实是指向了springsecurity自己的登录页面，从而实现认证--%>
<form id="loginForm" method="post">
    用户名:<input type="text" name="username"><br/>
    密码:<input type="password" name="password"><br/>
    验证码:<input type="text" name="imageCode"><img src="${pageContext.request.contextPath}/user/imageCode"/><br/>
    <input type="button" id="loginBtn" value="登录">

</form>

<script type="text/javascript">
    $(function () {
        $("#loginBtn").click(function () {
            $.post("${pageContext.request.contextPath}/login", $("#loginForm").serialize(), function (data) {
                    if (data.success) {
                        window.location.href = "${pageContext.request.contextPath}/user/index";
                    }else {
                        alert(data.mes);
                    }

                }
                , "json");
        });
    });
</script>
</body>
</html>
