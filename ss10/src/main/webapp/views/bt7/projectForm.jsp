<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head><title>Tạo Dự Án</title></head>
<body>
<h2>Tạo Dự Án Mới</h2>

<form:form modelAttribute="project" method="post" enctype="multipart/form-data" action="create">
  <p>Tên dự án: <form:input path="name" /></p>
  <p>Mô tả: <form:textarea path="description" rows="5" cols="30"/></p>
  <p>Tài liệu (PDF): <input type="file" name="files" multiple/></p>
  <p><input type="submit" value="Tạo Dự Án"/></p>
</form:form>

<a href="/projects">Xem danh sách dự án</a>
</body>
</html>