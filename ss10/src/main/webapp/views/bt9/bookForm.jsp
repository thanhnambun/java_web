<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head><title>Thêm Sách</title></head>
<body>
<h2>Thêm Sách Mới</h2>

<form:form modelAttribute="book" method="post" enctype="multipart/form-data" action="/library/add">
  <p>Tiêu đề: <form:input path="title" /></p>
  <p>Tác giả: <form:input path="author" /></p>
  <p>Mô tả: <form:textarea path="description" /></p>
  <p>File sách (PDF): <form:input path="file" type="file" /></p>
  <p><input type="submit" value="Thêm sách" /></p>
</form:form>

<c:if test="${not empty error}">
  <p style="color:red">${error}</p>
</c:if>

</body>
</html>