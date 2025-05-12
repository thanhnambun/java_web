<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Giỏ hàng</title>
</head>
<body>
<h2>Giỏ hàng của bạn</h2>

<table border="1" cellpadding="10" cellspacing="0">
    <thead>
    <tr>
        <th>Product ID</th>
        <th>Quantity</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${cartList}">
        <tr>
            <td>${item.productId}</td>
            <td>${item.quantity}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<h3>Tổng giá tiền: <c:out value="${totalAmount}" /> VNĐ</h3>

</body>
</html>
