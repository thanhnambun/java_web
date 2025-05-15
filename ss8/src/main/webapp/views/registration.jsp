<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Đăng ký</title></head>
<body>
<h2>Đăng ký tài khoản</h2>
<form:form method="post" action="/register" modelAttribute="user">
    Tên người dùng: <form:input path="name" />
    <div style="color:red"><form:errors path="name"/><c:out value="${nameError}" /></div>
    <br/>

    Email: <form:input path="email" />
    <div style="color:red"><form:errors path="email"/><c:out value="${emailError}" /></div>
    <br/>

    Số điện thoại: <form:input path="phone" />
    <div style="color:red"><form:errors path="phone"/><c:out value="${phoneError}" /></div>
    <br/>

    <input type="submit" value="Đăng ký" />
</form:form>
</body>
</html>
