<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h3>Thêm phòng ban</h3>
<form:form method="post" action="${pageContext.request.contextPath}/departments/save" modelAttribute="department">
    <p>Tên phòng ban: <form:input path="departmentName"/></p>
    <p>Mô tả: <form:input path="description"/></p>
    <p><input type="submit" value="Thêm mới"/></p>
</form:form>
