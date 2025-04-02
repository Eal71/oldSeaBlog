<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>博客详情</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/detail.css">
</head>
<body>
<!-- 导航栏 -->
<div class="nav">
    <img src="${pageContext.request.contextPath}/images/blog.jpg" alt="">
    <span>我的博客系统</span>

    <div class="spacer">
        <c:choose>
            <c:when test="${sessionScope.user != null}">
                <a href="${pageContext.request.contextPath}/home">管理博客</a>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/pages/users/login.jsp">登录</a>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<!-- 这里的.container作为页面的版心 -->
<div class="container">
    <!-- 左侧个人信息 -->
    <div class="left">
        <!-- 表示整个用户的信息区域 -->
        <div class="card">
            <img src="../images/${requestScope.user.image}" alt="${requestScope.user.nickname}">
            <h3>${requestScope.user.nickname}</h3>
            <div class="counter">
                <span>文章</span>
            </div>
            <div class="counter">
                <span>${requestScope.user.articleNumber}</span>
            </div>
        </div>
    </div>
    <!-- 右侧内容详情 -->
    <div class="right">
        <div class="title">
            <h1>${requestScope.article.title}</h1>
        </div>
        <p class="time">${requestScope.article.time}</p>
        <div class="content">
            ${requestScope.article.text}
        </div>
        <a href="${pageContext.request.contextPath}/index">返回首页</a>
    </div>
</div>
</body>
</html>
