<!-- File: error.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lỗi xảy ra</title>
</head>
<body>
<h2 style="color:red;">Xin lỗi! Có lỗi xảy ra.</h2>
<p><strong>Chi tiết lỗi:</strong> ${errorMessage}</p>
<a href="index.jsp">Quay lại trang chính</a>
</body>
</html>
