<%--
  Created by IntelliJ IDEA.
  User: dungp
  Date: 5/7/2025
  Time: 10:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="bt10.Product" %>
<!DOCTYPE html>
<html>
<head>
    <title>Danh sách sản phẩm</title>
    <meta charset="UTF-8">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Roboto', Arial, sans-serif;
            line-height: 1.6;
            margin: 20px;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .message {
            margin-top: 10px;
            font-weight: bold;
        }
        .message.success {
            color: green;
        }
        .message.error {
            color: red;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Danh sách sản phẩm</h2>
    <%
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Sách Lập trình Java", 150.0));
        products.add(new Product(2, "Điện thoại Samsung", 5000.0));
        products.add(new Product(3, "Laptop Dell", 12000.0));

        String message = (String) request.getAttribute("message");
        String messageClass = (message != null && message.contains("thành công")) ? "success" : "error";
    %>
    <% if (message != null) { %>
    <div class="message <%= messageClass %>"><%= message %></div>
    <% } %>
    <table>
        <tr>
            <th>ID</th>
            <th>Tên sản phẩm</th>
            <th>Giá</th>
            <th>Hành động</th>
        </tr>
        <% for (Product product : products) { %>
        <tr>
            <td><%= product.getId() %></td>
            <td><%= product.getName() %></td>
            <td><%= String.format("%.2f", product.getPrice()) %> VNĐ</td>
            <td>
                <form action="../bt10" method="post">
                    <input type="hidden" name="action" value="add">
                    <input type="hidden" name="id" value="<%= product.getId() %>">
                    <input type="hidden" name="name" value="<%= product.getName() %>">
                    <input type="hidden" name="price" value="<%= product.getPrice() %>">
                    <button type="submit">Thêm giỏ hàng</button>
                </form>
            </td>
        </tr>
        <% } %>
    </table>
    <br>
    <a href="bt10">Xem giỏ hàng</a>
</div>
</body>
</html>