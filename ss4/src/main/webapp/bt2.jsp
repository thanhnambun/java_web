<%--
  Created by IntelliJ IDEA.
  User: nambu
  Date: 5/8/2025
  Time: 4:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Đăng nhập</title>
</head>
<body>
<h2>Đăng nhập</h2>

<form action="bt2" method="post">
  Tên đăng nhập: <input type="text" name="username" required><br><br>
  Mật khẩu: <input type="password" name="password" required><br><br>
  <input type="submit" value="Đăng nhập">
</form>

<c:if test="${not empty error}">
  <p style="color:red;">${error}</p>
</c:if>

<c:if test="${not empty message}">
  <p style="color:green;">${message}</p>
</c:if>
</body>
</html>

