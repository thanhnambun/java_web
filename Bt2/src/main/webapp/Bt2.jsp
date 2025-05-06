<%--
  Created by IntelliJ IDEA.
  User: nambu
  Date: 5/6/2025
  Time: 4:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserInfoServlet </title>
</head>
<body>
<h2>Nhập thông tin</h2>
<form action="bt2" method="post">
    Họ tên: <input type="text" name="name" required><br><br>
    Tuổi: <input type="number" name="age" required><br><br>
    <button type="submit">Gửi</button>
</form>
</body>
</html>
