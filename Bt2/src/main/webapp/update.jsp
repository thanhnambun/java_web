<%@ page import="com.example.bt2.Product" %><%--
  Created by IntelliJ IDEA.
  User: nambu
  Date: 5/6/2025
  Time: 11:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sửa sản phẩm</title>
</head>
<body>
<%
    Product p = (Product) request.getAttribute("product");
%>
<form action="update" method="post">
    <input type="hidden" name="id" value="${product.id}">
    Tên: <input type="text" name="name" value="<%= p.getNameProduct() %>" required><br><br>
    Giá: <input type="number" name="price" value="<%= p.getPrice() %>" step="0.01" required><br><br>
    <button type="submit">Cập nhật</button>
</form>
</body>
</html>

