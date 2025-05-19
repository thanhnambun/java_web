<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Kết Quả Đặt Vé</title>
</head>
<body>
<h2>Đặt Vé Thành Công</h2>
<p><strong>Phim:</strong> ${ticket.movieTitle}</p>
<p><strong>Thời gian chiếu:</strong> ${ticket.showTime}</p>
<p><strong>Ghế đã chọn:</strong> ${ticket.seats}</p>
<p><strong>Tổng tiền:</strong> ${ticket.totalAmount} VND</p>
<br/>
<a href="booking">Đặt vé khác</a>
</body>
</html>