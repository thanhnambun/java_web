<%--
  Created by IntelliJ IDEA.
  User: nambu
  Date: 5/7/2025
  Time: 4:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Trò chơi đoán từ</title>
</head>
<body>
<h2>Game Đoán Từ</h2>

<p>Từ cần đoán: <strong>${masked}</strong></p>
<p>Số lượt đoán còn lại: <strong>${remaining}</strong></p>
<p>${message}</p>

<form method="post" action="bt9">
    <input type="text" name="guess" placeholder="Nhập từ của bạn..." />
    <button type="submit">Đoán</button>
</form>

<form method="get" action="bt9">
    <button type="submit">Chơi lại</button>
</form>
</body>
</html>

