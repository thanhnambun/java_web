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
      text-align: left;
    }
    th {
      background-color: #f2f2f2;
    }
    .form-container {
      margin-bottom: 20px;
    }
    .form-container input[type="text"] {
      padding: 5px;
      margin: 0 10px;
      width: 200px;
    }
    .form-container button {
      padding: 5px 15px;
      background-color: #4CAF50;
      color: white;
      border: none;
      cursor: pointer;
    }
    .form-container button:hover {
      background-color: #45a049;
    }
    .message {
      font-weight: bold;
      margin: 10px 0;
    }
    .success {
      color: green;
    }
    .error {
      color: red;
    }
  </style>
</head>
<body>
<h2>Danh Sách Sản Phẩm</h2>

<!-- Form tìm kiếm -->
<div class="form-container">
  <form action="bt5" method="post">
    <label for="id">Nhập ID sản phẩm cần tìm:</label>
    <input type="text" id="id" name="id" placeholder="Ví dụ: P001">
    <button type="submit">Tìm</button>
  </form>
</div>

<c:if test="${searchId != null}">
  <c:choose>
    <c:when test="${foundProduct != null}">
      <p class="message success">Sản phẩm được tìm thấy:</p>
      <table>
        <tr>
          <th>Tên</th>
          <th>Giá ($)</th>
          <th>Mô tả</th>
        </tr>
        <tr>
          <td>${foundProduct.name}</td>
          <td>${foundProduct.price}</td>
          <td>${foundProduct.description}</td>
        </tr>
      </table>
    </c:when>
    <c:otherwise>
      <p class="message error">Sản phẩm không tìm thấy</p>
    </c:otherwise>
  </c:choose>
</c:if>

<h3>Tất Cả Sản Phẩm</h3>
<table>
  <tr>
    <th>Tên</th>
    <th>Giá ($)</th>
    <th>Mô tả</th>
  </tr>
  <c:forEach var="product" items="${products}">
    <tr>
      <td>${product.name}</td>
      <td>${product.price}</td>
      <td>${product.description}</td>
    </tr>
  </c:forEach>
</table>
</body>
</html>