<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head><title>Danh mục</title></head>
<body>
<form:form method="POST" modelAttribute="category">
  <div>
    <label>Tên danh mục:</label>
    <form:input path="categoryName"/>
    <form:errors path="categoryName" cssClass="error"/>
  </div>
  <div>
    <label>Trạng thái:</label>
    <form:checkbox path="status"/> Kích hoạt
  </div>
  <button type="submit">Lưu</button>
</form:form>
<a href="${pageContext.request.contextPath}/categories">Quay lại danh sách</a>
</body>
</html>