<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bt10.Product" %>
<%@ page import="bt10.Manager" %>
<!DOCTYPE html>
<html>
<head>
    <title>Giỏ hàng</title>
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
        .total {
            margin-top: 10px;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Giỏ hàng</h2>
    <%
        Manager cartManager = (Manager) application.getAttribute("cartManager");
        List<Product> cart = (cartManager != null) ? cartManager.getCart() : new ArrayList<>();
    %>
    <% if (cart != null && !cart.isEmpty()) { %>
    <table>
        <tr>
            <th>ID</th>
            <th>Tên sản phẩm</th>
            <th>Giá</th>
            <th>Hành động</th>
        </tr>
        <% for (Product product : cart) { %>
        <tr>
            <td><%= product.getId() %></td>
            <td><%= product.getName() %></td>
            <td><%= String.format("%.2f", product.getPrice()) %> VNĐ</td>
            <td>
                <a href="../bt10?action=remove&id=<%= product.getId() %>"
                   onclick="return confirm('Bạn có chắc muốn xóa sản phẩm này?')">Xóa</a>
            </td>
        </tr>
        <% } %>
    </table>
    <div class="total">
        Tổng giá trị: <%= String.format("%.2f", cartManager.getTotalPrice()) %> VNĐ
    </div>
    <% } else { %>
    <p>Giỏ hàng trống.</p>
    <% } %>
    <br>
    <a href="products.jsp">Quay lại danh sách sản phẩm</a>
</div>
</body>
</html>