<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
  <title>Upload Tài liệu PDF</title>
</head>
<body>
<h2>Upload Tài liệu PDF</h2>

<c:if test="${not empty error}">
  <p style="color:red">${error}</p>
</c:if>

<form:form method="post" modelAttribute="document" action="/document/upload" enctype="multipart/form-data">
  <p>Title: <form:input path="title"/></p>
  <p>Description: <form:textarea path="description"/></p>
  <p>File PDF: <form:input path="file" type="file"/></p>
  <p><input type="submit" value="Upload"/></p>
</form:form>

</body>
</html>