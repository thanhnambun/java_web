<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Thành công</title></head>
<body>
<h2>${message}</h2>
<p>File e-book được upload tại:</p>
<a href="${fileUrl}" target="_blank">${fileUrl}</a>
<br><br>
<a href="/library/list">Xem danh sách sách</a>
</body>
</html>