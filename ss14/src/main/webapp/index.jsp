<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "SS14!" %>
</h1>
<br/>
<a href="B1/login">B1</a>
<br>
<a href="B2/products">B2</a>
<br>
<a href="B3/change-language">B3</a>
<br>
<a href="B4/cart">B4</a>
<br>
<a href="${pageContext.request.contextPath}/B5">B5</a>
<br>
<a href="${pageContext.request.contextPath}/register">B6</a>
<br>
<a href="${pageContext.request.contextPath}/login">B7</a>
</body>
</html>