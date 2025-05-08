<%--
  Created by IntelliJ IDEA.
  User: nambu
  Date: 5/8/2025
  Time: 7:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: dungp
  Date: 5/7/2025
  Time: 9:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Trang chủ</title>
    <meta charset="UTF-8">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Roboto', Arial, sans-serif;
            line-height: 1.6;
            margin: 20px;
            text-align: center;
        }
        button {
            padding: 10px 20px;
            font-size: 16px;
        }
    </style>
</head>
<body>
<div>
    <h2>Chào mừng đến với trang chủ</h2>
    <p>Nhấn nút dưới đây để đến trang chào mừng!</p>
    <form method="post">
        <button type="submit">Đi đến trang chào mừng</button>
    </form>
    <%
        if ("POST".equalsIgnoreCase(request.getMethod())) {
    %>
    <jsp:forward page="welcome.jsp" />
    <% } %>
</div>
</body>
</html>
