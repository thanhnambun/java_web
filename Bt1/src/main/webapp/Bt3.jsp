<%--
  Created by IntelliJ IDEA.
  User: nambu
  Date: 5/6/2025
  Time: 10:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Thông tin người dùng</title>
  <style>
    table {
      border-collapse: collapse;
      width: 40%;
      margin: 20px auto;
    }
    td, th {
      border: 1px solid #000;
      padding: 8px 12px;
      text-align: center;
    }
    h2 {
      text-align: center;
    }
  </style>
</head>
<body>
<h2>Thông tin người dùng</h2>
<table>
  <tr>
    <th>Họ và tên</th>
    <th>Tuổi</th>
  </tr>
  <tr>
    <td>${userName}</td>
    <td>${userAge}</td>
  </tr>
</table>
</body>
</html>
