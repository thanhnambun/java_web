<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html><html lang="vi">
<head><meta charset="UTF-8"><title>Đăng nhập</title></head>
<body>
<h1>Đăng nhập</h1>
<c:if test="${not empty error}">
    <p style="color:red;">${error}</p>
</c:if>
<form action="login" method="post">
    Username: <input name="username"/><br/>
    Password: <input type="password" name="password"/><br/>
    <button type="submit">Đăng nhập</button>
</form>
<p>Chưa có tài khoản? <a href="register">Đăng ký</a></p>
</body>
</html>