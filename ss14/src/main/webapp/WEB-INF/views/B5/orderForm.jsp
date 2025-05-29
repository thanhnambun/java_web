<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<form action="${order.id == null ? '/order' : '/order/update'}" method="post">
  <input type="hidden" name="id" value="${order.id}" />
  <label>Mã đơn hàng:</label>
  <input type="text" name="id" value="${order.id}" ${order.id != null ? "readonly" : ""} /><br/>
  <label>Tên sản phẩm:</label>
  <input type="text" name="productName" value="${order.productName}" /><br/>
  <label>Số lượng:</label>
  <input type="number" name="quantity" value="${order.quantity}" /><br/>
  <button type="submit">Lưu</button>
</form>
</body>
</html>