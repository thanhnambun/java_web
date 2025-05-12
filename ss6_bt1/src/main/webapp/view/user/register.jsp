<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html><html lang="vi">
<head><meta charset="UTF-8"><title>Đăng ký</title></head>
<body>
<h1>Đăng ký tài khoản</h1>
<c:if test="${not empty error}">
    <p style="color:red;">${error}</p>
</c:if>
<form action="register" method="post">
    Username: <input name="username" value="${param.username}"/><br/>
    Password: <input type="password" name="password"/><br/>
    Email:    <input name="email" value="${param.email}"/><br/>
    Phone:    <input name="phone" value="${param.phone}"/><br/>
    <button type="submit">Đăng ký</button>
</form>
<p>Đã có tài khoản? <a href="login">Đăng nhập</a></p>
</body>
</html>