<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<h2>Danh sách đơn hàng</h2>
<a href="/order">Thêm đơn hàng</a>
<table border="1">
  <tr>
    <th>Mã</th>
    <th>Tên sản phẩm</th>
    <th>Số lượng</th>
    <th>Hành động</th>
  </tr>
  <c:forEach items="${orders}" var="o">
    <tr>
      <td>${o.id}</td>
      <td>${o.productName}</td>
      <td>${o.quantity}</td>
      <td>
        <a href="/order/edit/${o.id}">Sửa</a>
        <a href="/order/delete/${o.id}">Xóa</a>
      </td>
    </tr>
  </c:forEach>
</table>
</body>
</html>