<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Quản Lý Giỏ Hàng</title>
</head>
<body>
<h2>Quản Lý Giỏ Hàng</h2>
<form action="${pageContext.request.contextPath}/B4/cart/add" method="post">
  <div class="form-group">
    <label for="name">Tên Sản Phẩm:</label>
    <input type="text" id="name" name="name" required />
  </div>
  <div class="form-group">
    <label for="quantity">Số Lượng:</label>
    <input type="number" id="quantity" name="quantity" required min="1" />
  </div>
  <button type="submit">Thêm Vào Giỏ Hàng</button>
</form>

<h3>Giỏ Hàng</h3>
<c:if test="${not empty cart}">
  <table>
    <tr>
      <th>Tên Sản Phẩm</th>
      <th>Số Lượng</th>
      <th>Hành Động</th>
    </tr>
    <c:forEach var="product" items="${cart}" varStatus="loop">
      <tr>
        <td>${product.name}</td>
        <td>${product.quantity}</td>
        <td><a href="/B4/cart/delete/${loop.index}">Xóa</a></td>
      </tr>
    </c:forEach>
  </table>
</c:if>
<c:if test="${empty cart}">
  <p>Giỏ hàng trống.</p>
</c:if>
</body>
</html>