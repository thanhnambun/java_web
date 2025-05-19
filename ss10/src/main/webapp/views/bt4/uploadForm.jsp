<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
  <title>Upload Avatar</title>
</head>
<body>
<h2>Upload Ảnh Đại Diện</h2>

<form:form modelAttribute="profile" method="post" enctype="multipart/form-data" action="uploads">
  <p>Username: <form:input path="username" /></p>
  <p>Avatar: <form:input path="avatar" type="file" /></p>
  <p><input type="submit" value="Tải lên" /></p>
</form:form>
</body>
</html>