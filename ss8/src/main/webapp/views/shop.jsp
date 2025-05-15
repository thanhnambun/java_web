<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Shop</title>
</head>
<body>
<h1>Shop</h1>
<table border="1">
    <tr>
        <th>Name</th>
        <th>Price</th>
        <th>Image</th>
        <th>Action</th>
    </tr>

    <c:forEach var="seed" items="${seeds}">
        <tr>
            <td>${seed.seedsName}</td>
            <td>${seed.price}</td>
            <td><img src="${seed.imageUrl}" alt="${seed.seedsName}" width="50"></td>
            <td>
                <form action="${pageContext.request.contextPath}/bt8910/buySeed" method="post">
                    <input type="hidden" name="seedId" value="${seed.id}">
                    <label>Quantity:</label>
                    <input type="number" name="quantity" min="1" max="10" value="1" required>
                    <button type="submit">Buy</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<br>
<a href="${pageContext.request.contextPath}/bt8910/farm">Go to Farm</a>
<a href="${pageContext.request.contextPath}/bt8910/warehouse">Go to Warehouse</a>
</body>
</html>