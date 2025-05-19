<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
  <title>Nhập Thông Tin Cá Nhân</title>
</head>
<body>
<h2>Form Nhập Thông Tin Người Dùng</h2>
<form:form method="POST" action="submitForm" modelAttribute="user">
  <table>
    <tr>
      <td>Tên:</td>
      <td><form:input path="name" /></td>
    </tr>
    <tr>
      <td>Tuổi:</td>
      <td><form:input path="age" /></td>
    </tr>
    <tr>
      <td>Địa chỉ:</td>
      <td><form:input path="address" /></td>
    </tr>
    <tr>
      <td colspan="2"><input type="submit" value="Gửi" /></td>
    </tr>
  </table>
</form:form>
</body>
</html>