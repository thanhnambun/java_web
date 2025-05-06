<%--
  Created by IntelliJ IDEA.
  User: nambu
  Date: 5/6/2025
  Time: 9:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Thông tin người dùng</title>
</head>
<body>
<h2>Thông tin đã đăng ký:</h2>
<p>Họ tên: <%= request.getAttribute("name") %></p>
<p>Email: <%= request.getAttribute("email") %></p>
<p>Mật khẩu: <%= request.getAttribute("password") %></p>
</body>
</html>
