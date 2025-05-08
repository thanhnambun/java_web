<%--
  Created by IntelliJ IDEA.
  User: dungp
  Date: 5/7/2025
  Time: 9:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Đăng ký người dùng</title>
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
      text-align: left;
    }
    label {
      display: inline-block;
      width: 100px;
    }
    input, button {
      padding: 5px;
      width: 200px;
    }
    .message {
      margin-top: 15px;
      font-weight: bold;
    }
  </style>
</head>
<body>
<div class="container">
  <h2>Đăng ký người dùng</h2>
  <form method="post">
    <div class="form-group">
      <label for="name">Tên:</label>
      <input type="text" id="name" name="name" required>
    </div>
    <div class="form-group">
      <label for="email">Email:</label>
      <input type="email" id="email" name="email" required>
    </div>
    <div class="form-group">
      <label for="password">Mật khẩu:</label>
      <input type="password" id="password" name="password" required>
    </div>
    <button type="submit">Đăng ký</button>
  </form>

  <%
    if ("POST".equalsIgnoreCase(request.getMethod())) {
      String name = request.getParameter("name");
      String email = request.getParameter("email");
      String password = request.getParameter("password");

      if (name != null && email != null && password != null && !name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
  %>
  <div class="message" style="color: green;">
    Đăng ký thành công! Chào mừng <%= name %> với email <%= email %>.
  </div>
  <%
  } else {
  %>
  <div class="message" style="color: red;">
    Vui lòng điền đầy đủ thông tin!
  </div>
  <%
      }
    }
  %>
</div>
</body>
</html>