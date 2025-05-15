<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Warehouse</title>
</head>
<body>
<h1>Your Warehouse</h1>

<table border="1">
    <tr>
        <th>Seed Name</th>
        <th>Quantity</th>
        <th>Price</th>
    </tr>

    <c:forEach var="item" items="${warehouse}">
        <tr>
            <td>${item.seed.seedsName}</td>
            <td>${item.quantity}</td>
            <td>${item.seed.price}</td>
        </tr>
    </c:forEach>
</table>

<br>
<a href="${pageContext.request.contextPath}/bt8910/shop">Go to Shop</a>
<a href="${pageContext.request.contextPath}/bt8910/farm">Go to Farm</a>
</body>
</html>