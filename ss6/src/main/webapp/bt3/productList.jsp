<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Danh sách sản phẩm</title>
    <style>
        .product-container {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
        }
        .product-card {
            border: 1px solid #ddd;
            padding: 10px;
            width: 200px;
            text-align: center;
        }
        img {
            max-width: 100%;
            height: 150px;
            object-fit: contain;
        }
        form {
            margin-top: 10px;
        }
    </style>
</head>
<body>
<h2>Danh sách sản phẩm</h2>

<div class="product-container">
    <c:forEach var="product" items="${productList}">
        <div class="product-card">
            <img src="${product.imageUrl}" alt="${product.name}" />
            <h4>${product.name}</h4>
            <p>Giá: ${product.price} VNĐ</p>
            <form method="post" action="${pageContext.request.contextPath}/product">
                <input type="hidden" name="productId" value="${product.id}" />
                <button type="submit">Thêm vào giỏ</button>
            </form>
        </div>
    </c:forEach>
</div>

<a href="cart">Xem giỏ hàng</a>
</body>
</html>

