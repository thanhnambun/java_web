<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Danh Sách Sinh Viên</title>
    <style>
        table {
            border-collapse: collapse;
            width: 80%;
            margin: 20px auto;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
        h2, p {
            text-align: center;
        }
    </style>
</head>
<body>
<h2>Danh Sách Sinh Viên</h2>

<table>
    <tr>
        <th>ID</th>
        <th>Tên</th>
        <th>Tuổi</th>
        <th>Điểm Trung Bình</th>
    </tr>
    <c:set var="count" value="0" scope="page"/>

    <c:forEach var="student" items="${students}">
        <tr>
            <td>${student.id}</td>
            <td>${student.name}</td>
            <td>${student.age}</td>
            <td>${student.gpa}</td>
        </tr>
        <c:if test="${student.gpa > 7.0}">
            <c:set var="count" value="${count + 1}" scope="page"/>
        </c:if>
    </c:forEach>
</table>

<p>Tổng số sinh viên có điểm trung bình lớn hơn 7.0: ${count}</p>
</body>
</html>