<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Quản lý phòng ban</title>
</head>
<body>
<h2>Danh sách phòng ban</h2>

<form method="get" action="departments/search">
    Tìm theo tên:
    <input type="text" name="name"/>
    <button type="submit">Tìm kiếm</button>
</form>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Tên phòng ban</th>
        <th>Mô tả</th>
        <th>Trạng thái</th>
        <th>Thao tác</th>
    </tr>
    <c:forEach items="${departments}" var="d">
        <tr>
            <td>${d.departmentId}</td>
            <td>${d.departmentName}</td>
            <td>${d.description}</td>
            <td>
                <c:choose>
                    <c:when test="${d.status}">Hoạt động</c:when>
                    <c:otherwise>Không hoạt động</c:otherwise>
                </c:choose>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/departments/edit?id=${d.departmentId}">Sửa</a>
                <a href="${pageContext.request.contextPath}/departments/delete?id=${d.departmentId}" onclick="return confirm('Xác nhận xóa?')">Xóa</a>

            </td>
        </tr>
    </c:forEach>
</table>

<h3><a href="departments/add">Thêm phòng ban</a></h3>

</body>
</html>
