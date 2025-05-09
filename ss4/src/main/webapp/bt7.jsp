<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Danh Sách Sản Phẩm</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            text-align: center;
        }
        table {
            border-collapse: collapse;
            width: 80%;
            margin: 20px auto;
        }
        th, td {
            border: 1px solid black;
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
        .form-container {
            margin-bottom: 20px;
        }
        .form-container input[type="number"] {
            padding: 5px;
            margin: 0 10px;
            width: 120px;
        }
        .form-container input[type="submit"] {
            padding: 5px 15px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }
        .form-container input[type="submit"]:hover {
            background-color: #45a049;
        }
        .message {
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>
<h2>Danh Sách Sản Phẩm</h2>

<div class="form-container">
    <form action="bt7" method="post">
        <label>Giá tối thiểu:</label>
        <input type="number" name="minPrice" step="0.01" min="0" placeholder="0.0">
        <label>Giá tối đa:</label>
        <input type="number" name="maxPrice" step="0.01" min="0" placeholder="0.0">
        <input type="submit" value="Lọc">
    </form>
</div>

<c:choose>
    <c:when test="${not empty products}">
        <table>
            <tr>
                <th>ID</th>
                <th>Tên Sản Phẩm</th>
                <th>Giá ($)</th>
            </tr>
            <c:forEach var="product" items="${products}">
                <tr>
                    <td>${product.id}</td>
                    <td>${product.name}</td>
                    <td>${product.price}</td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <p class="message">Không có sản phẩm nào trong khoảng giá này</p>
    </c:otherwise>
</c:choose>
</body>
</html>