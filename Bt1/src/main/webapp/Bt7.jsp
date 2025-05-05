<%--
  Created by IntelliJ IDEA.
  User: nambu
  Date: 5/5/2025
  Time: 10:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.bt1.StudentTicket" %>
<!DOCTYPE html>
<html>
<head>
    <title>Danh sách học sinh đăng ký vé xe</title>
</head>
<body>
<h2>Danh sách học sinh đã đăng ký vé xe</h2>

<table border="1">
    <tr>
        <th>Họ và tên</th>
        <th>Lớp</th>
        <th>Loại xe</th>
        <th>Biển số xe</th>
    </tr>

    <%
        List<StudentTicket> students = (List<StudentTicket>) request.getAttribute("studentList");

        if (students != null && !students.isEmpty()) {
            for (StudentTicket student : students) {
    %>
    <tr>
        <td><%= student.getFullName() %>
        </td>
        <td><%= student.getClassName() %>
        </td>
        <td><%= student.getVehicleType() %>
        </td>
        <td><%= student.getLicensePlate() %>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="4">Chưa có học sinh nào đăng ký.</td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>

