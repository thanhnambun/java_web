<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Danh sách sản phẩm</title>
    <style>
        table {
            border-collapse: collapse;
            width: 80%;
            margin: 20px auto;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .message {
            text-align: center;
            color: #d32f2f;
            margin-top: 20px;
        }
    </style>
</head>
<body>
<h1 style="text-align: center;">Danh sách sản phẩm</h1>

<c:choose>
    <c:when test="${not empty products}">
        <table>
            <tr>
                <th>Tên</th>
                <th>Giá</th>
                <th>Mô tả</th>
            </tr>
            <c:forEach var="product" items="${products}">
                <c:if test="${product.price != 0 or product.description != ''}">
                    <tr>
                        <td>${product.name}</td>
                        <td>
                            <c:choose>
                                <c:when test="${product.price == 0}">
                                    Không có thông tin về giá
                                </c:when>
                                <c:otherwise>
                                    $${product.price}
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${product.description}</td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <p class="message">Sản phẩm không có thông tin</p>
    </c:otherwise>
</c:choose>
</body>
</html>