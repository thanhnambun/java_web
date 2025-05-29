<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Login</title>
</head>
<body>
<h2>Login</h2>

<form:form method="post" modelAttribute="user">
  <div>
    <label>Username:</label>
    <form:input path="username" />
  </div>
  <div>
    <label>Password:</label>
    <form:password path="password" />
  </div>
  <div>
    <input type="checkbox" name="remember" /> Ghi nhớ tôi
  </div>
  <div>
    <input type="submit" value="Login" />
  </div>
  <div style="color: red;">
      ${message}
  </div>
</form:form>
</body>
</html>