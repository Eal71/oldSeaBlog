<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>博客编辑页</title>
    <link rel="stylesheet" href="../../css/common.css">
    <link rel="stylesheet" href="../../css/edit.css">
</head>
<body>
<!-- 导航栏 -->
<nav class="nav">
    <img src="../../images/blog.jpg" alt="">
    <span>我的博客系统</span>
    <div class="spacer"></div>
    <a href="${pageContext.request.contextPath}/index">首页</a>
</nav>
<!-- 包裹整个博客编辑页内容的顶级容器 -->
<div class="blog-edit-container">
    <form action="${pageContext.request.contextPath}/insert" method="post">
    <div class="title">
        <input type="text" name="articleTitle" placeholder="文章标题" required>
        <input type="text" name="articleSummary" placeholder="文章摘要" required>
        <button type="submit">发布文章</button>
    </div>
    <div class="editor">
        <textarea id="article-content" name="articleContent" placeholder="在此处编写您的文章..." required></textarea>
    </div>
    </form>
</div>
</body>
</html>
