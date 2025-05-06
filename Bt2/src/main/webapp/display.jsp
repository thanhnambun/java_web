<%--
  Created by IntelliJ IDEA.
  User: nambu
  Date: 5/6/2025
  Time: 5:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Thông tin người dùng</title>
</head>
<body>
<h2>Thông tin đã nhập:</h2>
<p>Họ tên: <%= request.getAttribute("name") %></p>
<p>Tuổi: <%= request.getAttribute("age") %></p>
</body>
</html>