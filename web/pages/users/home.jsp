<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>个人主页</title>
    <link rel="stylesheet" href="../../css/common.css">
    <link rel="stylesheet" href="../../css/home.css">
</head>
<body>

<div class="nav">
    <img src="../../images/blog.jpg" alt="">
    <span>我的博客系统</span>
    <div class="index">
        <a href="${pageContext.request.contextPath}/index">首页</a>
    </div>

    <div class="spacer">
        <a href=${pageContext.request.contextPath}"/pages/article/add.jsp">写博客</a>
    </div>
</div>

<div class="container">
    <aside class="left">
        <div class="as">
            <img src="../../images/文件.png" alt="文件" class="icon file-icon">
            <span>文章管理</span>
        </div>
        <div class="as">
        </div>
    </aside>
    <main class="right">
        <div class="blog-list-container">
            <c:choose>
                <c:when test="${not empty requestScope.articles}">
                    <ul class="article-list">
                        <c:forEach var="article" items="${requestScope.articles}">
                            <li class="article-item">
                                <div class="article-title">${article.title}</div>
                                <div class="article-time">${article.time}</div>
                                <div class="aa">
                                    <a href="${pageContext.request.contextPath}/echo?id=${article.id}">编辑</a>
                                    <a href="${pageContext.request.contextPath}/delete?id=${article.id}">删除</a>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </c:when>
                <c:otherwise>
                    <h6 class="no">无文章</h6>
                </c:otherwise>
            </c:choose>
        </div>
    </main>
</div>
</body>
</html>