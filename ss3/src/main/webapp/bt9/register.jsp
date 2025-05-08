<%--
  Created by IntelliJ IDEA.
  User: nambu
  Date: 5/8/2025
  Time: 6:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Đăng ký người dùng</title>
</head>
<body>
<h2>Đăng ký người dùng</h2>
<form action="../bt9" method="post">
    Tên: <input type="text" name="name" required><br><br>
    Email: <input type="email" name="email" required><br><br>
    <input type="submit" value="Đăng ký">
</form>
<br>
<a href="useList.jsp">Xem danh sách người dùng</a>
</body>
</html>

