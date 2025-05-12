
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Employee List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">
<h2>Employee List</h2>
<a href="${pageContext.request.contextPath}/employee?action=add" class="btn btn-primary mb-3">Add New Employee</a>

<!-- Search Form -->
<form action="${pageContext.request.contextPath}/employee" method="get" class="mb-3">
    <div class="input-group w-25">
        <input type="number" name="id" class="form-control" placeholder="Search by ID" required>
        <input type="hidden" name="action" value="search">
        <button type="submit" class="btn btn   <button type="submit" class="btn btn-outline-secondary">Search</button>
    </div>
</form>

<!-- Employee Table -->
<table class="table table-bordered">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Date of Birth</th>
        <th>Phone</th>
        <th>Salary</th>
        <th>Position</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="employee" items="${employees}">
        <tr>
            <td>${employee.id}</td>
            <td>${employee.name}</td>
            <td><fmt:formatDate value="${employee.birthday}" pattern="yyyy-MM-dd"/></td>
            <td>${employee.phone}</td>
            <td><fmt:formatNumber value="${employee.salary}" type="number" minFractionDigits="2" maxFractionDigits="2"/></td>
            <td>${employee.position}</td>
            <td>
                <a href="${pageContext.request.contextPath}/employee?action=edit&id=${employee.id}" class="btn btn-sm btn-warning">Edit</a>
                <form action="${pageContext.request.contextPath}/employee" method="post" style="display:inline;">
                    <input type="hidden" name="id" value="${employee.id}">
                    <input type="hidden" name="action" value="delete">
                    <button type="submit" class="btn btn-sm btn-danger" onclick="return confirm('Are you sure you want to delete this employee?')">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
