<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
  <title>Nhập Thông Tin</title>
</head>
<body>
<h2>Form Nhập Thông Tin</h2>
<form:form method="POST" action="addProduct" modelAttribute="product">
  <table>
    <tr>
      <td>Tên:</td>
      <td><form:input path="name" /></td>
    </tr>
    <tr>
      <td>Giá:</td>
      <td><form:input path="price" /></td>
    </tr>
    <tr>
      <td>Mô tả:</td>
      <td><form:input path="description" /></td>
    </tr>
    <tr>
      <td colspan="2"><input type="submit" value="Gửi" /></td>
    </tr>
  </table>
</form:form>
</body>
</html>