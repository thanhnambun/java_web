<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Danh Sách Sách</title></head>
<body>
<h2>Danh Sách Sách</h2>

<c:forEach var="book" items="${books}">
  <div style="margin-bottom: 15px;">
    <strong>Tiêu đề:</strong> ${book.title}<br>
    <strong>Tác giả:</strong> ${book.author}<br>
    <strong>Mô tả:</strong> ${book.description}
    <strong>ảnh :</strong> <img src="${fileUrl}">
  </div>
</c:forEach>

<a href="/library/add">Thêm sách mới</a>
</body>
</html>