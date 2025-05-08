<%--
  Created by IntelliJ IDEA.
  User: dungp
  Date: 5/7/2025
  Time: 9:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Máy tính cộng đơn giản</title>
  <meta charset="UTF-8">
  <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
  <style>
    body {
      font-family: 'Roboto', Arial, sans-serif;
      line-height: 1.6;
      margin: 20px;
      text-align: center;
    }
    .container {
      max-width: 400px;
      margin: 0 auto;
    }
    .form-group {
      margin-bottom: 15px;
    }
    input, button {
      padding: 5px;
    }
    .result {
      margin-top: 15px;
      font-weight: bold;
    }
  </style>
</head>
<body>
<div class="container">
  <h2>Máy tính cộng đơn giản</h2>
  <form method="post">
    <div class="form-group">
      <label for="num1">Số thứ 1:</label>
      <input type="number" id="num1" name="num1" step="any" required>
    </div>
    <div class="form-group">
      <label for="num2">Số thứ 2:</label>
      <input type="number" id="num2" name="num2" step="any" required>
    </div>
    <button type="submit">Tính tổng</button>
  </form>

  <%
    if ("POST".equalsIgnoreCase(request.getMethod())) {
      try {
        double num1 = Double.parseDouble(request.getParameter("num1"));
        double num2 = Double.parseDouble(request.getParameter("num2"));
        double sum = num1 + num2;
  %>
  <div class="result">
    Kết quả: <%= num1 %> + <%= num2 %> = <%= sum %>
  </div>
  <%
  } catch (NumberFormatException e) {
  %>
  <div class="result" style="color: red;">
    Vui lòng nhập số hợp lệ!
  </div>
  <%
      }
    }
  %>
</div>
</body>
</html>