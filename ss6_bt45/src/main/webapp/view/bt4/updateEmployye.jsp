
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Update Employee</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">
<h2>Update Employee</h2>
<form action="${pageContext.request.contextPath}/employee" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="id" value="${employee.id}">
    <div class="mb-3">
        <label for="name" class="form-label">Name</label>
        <input type="text" class="form-control" id="name" name="name" value="${employee.name}" required>
    </div>
    <div class="mb-3">
        <label for="birthday" class="form-label">Date of Birth</label>
        <input type="date" class="form-control" id="birthday" name="birthday" value="${employee.birthday}" required>
    </div>
    <div class="mb-3">
        <label for="phone" class="form-label">Phone</label>
        <input type="text" class="form-control" id="phone" name="phone" value="${employee.phone}" required>
    </div>
    <div class="mb-3">
        <label for="salary" class="form-label">Salary</label>
        <input type="number" step="0.01" class="form-control" id="salary" name="salary" value="${employee.salary}" required>
    </div>
    <div class="mb-3">
        <label for="position" class="form-label">Position</label>
        <input type="text" class="form-control" id="position" name="position" value="${employee.position}" required>
    </div>
    <button type="submit" class="btn btn-primary">Update</button>
    <a href="${pageContext.request.contextPath}/employee" class="btn btn-secondary">Cancel</a>
</form>
</body>
</html>
