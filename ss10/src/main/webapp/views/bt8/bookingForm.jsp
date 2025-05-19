<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
  <title>Đặt Vé Xem Phim</title>
</head>
<body>
<h2>Form Đặt Vé Xem Phim</h2>

<form:form modelAttribute="ticket" method="post" action="book">
  <p>Phim: <form:input path="movieTitle" /></p>
  <p>Thời gian chiếu: <form:input path="showTime" type="datetime-local"/>
  <p>Chọn ghế:
    <form:checkbox path="seats" value="A1" /> A1
    <form:checkbox path="seats" value="A2" /> A2
    <form:checkbox path="seats" value="A3" /> A3
    <form:checkbox path="seats" value="B1" /> B1
    <form:checkbox path="seats" value="B2" /> B2
  </p>
  <input type="submit" value="Đặt vé"/>
</form:form>

</body>
</html>