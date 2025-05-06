<%--
  Created by IntelliJ IDEA.
  User: nambu
  Date: 5/6/2025
  Time: 5:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Đăng ký thành công</title>
</head>
<%
    String name = (String) session.getAttribute("name");
%>
<body>
<h2>Cảm ơn <%= name %> đã đăng ký!</h2>
<p>Chúng tôi đã ghi nhận thông tin của bạn.</p>
</body>
</html>
