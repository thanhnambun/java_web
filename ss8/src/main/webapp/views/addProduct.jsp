<%--
  Created by IntelliJ IDEA.
  User: nambu
  Date: 5/15/2025
  Time: 8:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thêm mới sản phẩm</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/products/save" method="post" modelAttribute="product">
    Tên sản phẩm: <input type="text" name="name" required /><br/>
    Số lượng : <input type="text" name="quantity" required /><br/>
    Giá: <input type="number" step="0.01" name="price" required /><br/>
    <button type="submit">Lưu</button>
</form>
<c:if test="${not empty message}">
    <div style="color:green;">${message}</div>
</c:if>
<p><a href="products">quay về trang sản phẩm</a></p>
</body>
</html>
