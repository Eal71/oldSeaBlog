<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../css/login.css" type="text/css">
    <title>登录界面</title>
</head>
<body>
<div class="box">
    <div class="left"></div>
    <div class="right">
        <h4>登录</h4>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <input class="acc" type="text" name="username" placeholder="用户名" required>
            <input class="acc" type="password" name="password" placeholder="密码" required>
            <input class="submit" type="submit" value="提交">
        </form>
        <div class="fn">
            <a href="register.jsp">没有账号？注册</a>
        </div>
        <div class="terms">同意网站服务条款</div>
    </div>
</div>
</body>
<script>
    onload = function() {
        const error = "<%= request.getParameter("error") %>";
        if (error) {
            if (error === "1") {
                alert("用户名或密码错误，请重试。");
            } else if (error === "2") {
                alert("系统错误，请稍后再试。");
            }
        }
    };
</script>
</html>