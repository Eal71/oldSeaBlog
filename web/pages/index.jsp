<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>博客首页</title>
    <link rel="stylesheet" href="../css/common.css">
    <link rel="stylesheet" href="../css/index.css">
</head>
<body>
<!-- 导航栏 -->
<div class="nav">
    <img src="../images/blog.jpg" alt="">
    <span>我的博客系统</span>

    <!-- 搜索表单 -->
    <div class="search-bar">
        <input type="text" placeholder="搜索..." id="searchInput">
        <button>
            <img src="../images/搜索按钮.png" alt="Search Icon" class="search-icon">
        </button>
    </div>

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
<div class=" container">
    <!-- 左侧个人信息 -->
    <div class="left">
        <!-- 表示整个用户的信息区域 -->
        <div class="card">
            <div class="profile-image">
                <img src="../images/${requestScope.user.image}" alt="${requestScope.user.nickname}" class="default-img">
                <img src="../images/hoverImage.jpg" alt="${requestScope.user.nickname}" class="hover-img">
            </div>
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
        <!-- 博客列表容器 -->
        <div class="blog-list-container">
            <ul>
                <c:forEach var="article" items="${requestScope.articles}">
                    <li class="">
                        <div class="article-title">${article.title}</div>
                        <div class="article-time">${article.time}</div>
                        <div class="article-summary">${article.summary}</div>
                        <div class="aa">
                            <a href="${pageContext.request.contextPath}/detail?id=${article.id}">查看全文
                                &gt;&gt;</a>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
</body>
</html>