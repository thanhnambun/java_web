<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Form Nhập Thông Tin</title>
    <style>
        .error { color: red; }
    </style>
</head>
<body>
<h2>Form Nhập Thông Tin Người Dùng</h2>

<form:form modelAttribute="user" action="/submit" method="post">
    <table>
        <tr>
            <td>Name:</td>
            <td><form:input path="name" /></td>
            <td><form:errors path="name" cssClass="error" /></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><form:input path="email" /></td>
            <td><form:errors path="email" cssClass="error" /></td>
        </tr>
        <tr>
            <td>Phone:</td>
            <td><form:input path="phone" /></td>
            <td><form:errors path="phone" cssClass="error" /></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><form:password path="password" /></td>
            <td><form:errors path="password" cssClass="error" /></td>
        </tr>
        <tr>
            <td>Vai trò:</td>
            <td>
                <form:select path="role">
                    <form:option value="">-- Chọn --</form:option>
                    <form:option value="user">Người dùng</form:option>
                    <form:option value="admin">Admin</form:option>
                </form:select>
            </td>
            <td><form:errors path="role" cssClass="error" /></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Gửi thông tin"/></td>
        </tr>
    </table>
</form:form>

</body>
</html>
