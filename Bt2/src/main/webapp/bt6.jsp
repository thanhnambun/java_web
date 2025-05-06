<%@ page import="com.example.bt2.Product" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Danh sách sản phẩm</title>
</head>
<body>
<form action="bt6" method="post">

    Tên: <input type="text" name="name" required><br><br>
    Giá: <input type="text" name="price" required><br><br>
    <button type="submit">Thêm</button>
</form>

<br>
<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>Tên sản phẩm</th>
        <th>Giá</th>
        <th>Hành động</th>
    </tr>

    <%
        List<Product> list = (List<Product>) request.getAttribute("products");
        if (list != null) {
            for (Product p : list) {
    %>
    <tr>
        <td><%= p.getId() %></td>
        <td><%= p.getNameProduct() %></td>
        <td><%= p.getPrice() %></td>
        <td><a href="bt6?action=delete&id=<%= p.getId() %>">Xóa</a></td>
        <td><a href="bt6?action=update&id=<%= p.getId() %>">sửa</a></td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>
