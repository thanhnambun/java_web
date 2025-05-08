<%@ page import="java.util.List" %>
<%@ page import="bt6.Product" %>
<%--
  Created by IntelliJ IDEA.
  User: nambu
  Date: 5/7/2025
  Time: 4:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Thêm sản phẩm và danh sách</title>
</head>

<body>
<h2>Thêm Sản Phẩm</h2>
<form action="bt6" method="post">
  ID: <input type="text" name="id"><br>
  Tên: <input type="text" name="productName"><br>
  Giá: <input type="text" name="price"><br>
  Mô tả: <input type="text" name="description"><br>
  Số lượng: <input type="text" name="quantity"><br>
  Trạng thái: <input type="text" name="status"><br>
  <button type="submit">Thêm</button>
</form>
<h2>Danh sách sản phẩm</h2>
<table border="1">
  <tr>
    <th>ID</th><th>Tên</th><th>Giá</th><th>Mô tả</th><th>Số lượng</th><th>Trạng thái</th>
  </tr>
  <%
    List<Product> products = (List<Product>) application.getAttribute("productList");
    if (products != null) {
      for (Product p : products) {
  %>
  <tr>
    <td><%= p.getId() %></td>
    <td><%= p.getNameProduct() %></td>
    <td><%= p.getPrice() %></td>
    <td><%= p.getDescription() %></td>
    <td><%= p.getQuantity() %></td>
    <td><%= p.isStatus() %></td>
  </tr>
  <%
    }
  } else {
  %>
  <tr><td colspan="6">Chưa có sản phẩm nào.</td></tr>
  <%
    }
  %>
</table>
</body>
</html>

