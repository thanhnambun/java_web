<%--
  Created by IntelliJ IDEA.
  User: nambu
  Date: 5/8/2025
  Time: 4:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Danh sách sản phẩm</title>
</head>
<body>
<h2>Danh sách sản phẩm</h2>
<table border="1" cellpadding="10">
    <tr>
        <th>ID</th>
        <th>Tên sản phẩm</th>
        <th>Giá</th>
        <th>Mô tả</th>
    </tr>
    <c:forEach var="product" items="${products}">
        <tr>
            <td>${product.id}</td>
            <td>${product.productName}</td>
            <td><fmt:formatNumber value="${product.price}" type="currency" />${product.price}</td>
            <td>${product.description}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
