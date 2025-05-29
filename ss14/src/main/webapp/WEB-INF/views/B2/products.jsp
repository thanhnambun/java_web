<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Quản Lý Sản Phẩm</title>
</head>
<body>
<h2>Quản Lý Sản Phẩm</h2>
<form action="${pageContext.request.contextPath}/B2/products/add" method="post">
  <div class="form-group">
    <label for="name">Tên Sản Phẩm:</label>
    <input type="text" id="name" name="name" required />
  </div>
  <div class="form-group">
    <label for="price">Giá:</label>
    <input type="number" id="price" name="price" required />
  </div>
  <button type="submit">Thêm Sản Phẩm</button>
</form>

<h3>Danh Sách Sản Phẩm</h3>
<c:if test="${not empty products}">
  <table>
    <tr>
      <th>Tên Sản Phẩm</th>
      <th>Giá</th>
      <th>Hành Động</th>
    </tr>
    <c:forEach var="product" items="${products}" varStatus="loop">
      <tr>
        <td>${product.name}</td>
        <td>${product.price}</td>
        <td><a href="/B2/products/delete/${loop.index}">Xóa</a></td>
      </tr>
    </c:forEach>
  </table>
</c:if>
<c:if test="${empty products}">
  <p>Chưa có sản phẩm nào.</p>
</c:if>
</body>
</html>