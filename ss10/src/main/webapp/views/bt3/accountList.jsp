
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Danh sách tài khoản</title>
</head>
<body>
<h2>Danh sách tài khoản đã đăng ký:</h2>
<table border="1">
  <tr>
    <th>Username</th>
    <th>Email</th>
  </tr>
  <c:forEach var="acc" items="${accounts}">
    <tr>
      <td>${acc.username}</td>
      <td>${acc.email}</td>
    </tr>
  </c:forEach>
</table>
</body>
</html>
