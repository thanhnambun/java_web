
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="model.bt2.Student" %>
<%
    Student student = (Student) request.getAttribute("student");
%>
<html>
<head>
    <title>Sửa thông tin sinh viên</title>
</head>
<body>
<h2>Sửa sinh viên</h2>
<form action="${pageContext.request.contextPath}/bt2" method="post">
    <input type="hidden" name="id" value="<%= student.getId() %>">
    Họ tên: <input type="text" name="name" value="<%= student.getName() %>" required><br><br>
    Tuổi: <input type="number" name="age" value="<%= student.getAge() %>" required><br><br>
    Địa chỉ: <input type="text" name="address" value="<%= student.getAddress() %>" required><br><br>
    <input type="submit" value="Cập nhật">
</form>
</body>
</html>
