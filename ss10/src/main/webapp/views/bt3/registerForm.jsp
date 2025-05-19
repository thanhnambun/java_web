<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
  <title>Đăng ký tài khoản</title>
</head>
<body>
<h2>Form Đăng Ký</h2>
<form:form method="post" modelAttribute="account" action="register">
  <p>Username: <form:input path="username" /></p>
  <p>Password: <form:password path="password" /></p>
  <p>Email: <form:input path="email" /></p>
  <p><input type="submit" value="Đăng ký" /></p>
</form:form>
</body>
</html>