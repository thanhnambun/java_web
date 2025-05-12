<%--
  Created by IntelliJ IDEA.
  User: nambu
  Date: 5/12/2025
  Time: 8:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Catalog</title>
</head>
<body>
<h3>Create New Catalog</h3>
<form action="<%=request.getContextPath()%>/CategoriesController?action=Create" method="post">
    <label for="catalogName">Catalog Name</label>
    <input type="text" id="catalogName" name="catalogName"/><br>
    <label for="description">Description</label>
    <input type="text" id="description" name="description"/><br>
    <label for="active">Status</label>
    <input type="radio" id="active" name="status" value="true" checked/><label for="active">Active</label>
    <input type="radio" id="inActive" name="status" value="false"/><label for="inActive">Inactive</label><br>
    <input type="submit" value="Create"/>
</form>
</body>
</html>
