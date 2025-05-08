<%--
  Created by IntelliJ IDEA.
  User: nambu
  Date: 5/7/2025
  Time: 4:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Giới thiệu bản thân</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 40px;
      background-color: #f5f5f5;
    }
    .profile {
      background-color: #ffffff;
      padding: 30px;
      border-radius: 10px;
      max-width: 600px;
      margin: auto;
      box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }
    h1 {
      color: #333;
    }
    p {
      line-height: 1.6;
    }
  </style>
</head>
<body>

<div class="profile">
  <h1>Xin chào, tôi là <%= "Nguyễn Thành Nam" %></h1>
  <p><strong>Mã sinh viên:</strong> <%= "B23DTCN227" %></p>
  <p><strong>Lớp:</strong> <%= "CNTT - Hệ đào tạo từ xa" %></p>
  <p>Tôi hiện đang học tại Học viện Công nghệ Bưu chính Viễn thông. Tôi yêu thích lập trình Java và đang học về JSP/Servlets.</p>
  <p>Mục tiêu của tôi là trở thành một lập trình viên full-stack chuyên nghiệp trong tương lai.</p>
</div>
</body>
</html>

