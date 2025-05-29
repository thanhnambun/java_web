
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>B1</title>
</head>
<body>
<h2>Đăng Nhập</h2>
<c:if test="${not empty error}">
    <p class="error">${error}</p>
</c:if>
<form action="${pageContext.request.contextPath}/B1/login" method="post">
    <div class="form-group">
        <label for="username">Tên người dùng:</label>
        <input type="text" id="username" name="username" required />
    </div>
    <div class="form-group">
        <label for="password">Mật khẩu:</label>
        <input type="password" id="password" name="password" required />
    </div>
    <button type="submit">Đăng Nhập</button>
</form>
</body>
</html>