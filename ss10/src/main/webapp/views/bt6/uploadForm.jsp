<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head><title>Upload File lên Cloud</title></head>
<body>
<h2>Upload File lên Cloud</h2>

<c:if test="${not empty error}">
  <p style="color:red">${error}</p>
</c:if>

<form:form modelAttribute="uploadFile" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/uploadBt6">
  <p>Mô tả: <form:input path="description" /></p>
  <p>File: <form:input path="file" type="file" /></p>
  <p><input type="submit" value="Upload"/></p>
</form:form>
</body>
</html>