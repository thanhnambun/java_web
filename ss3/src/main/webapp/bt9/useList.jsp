<%--
  Created by IntelliJ IDEA.
  User: nambu
  Date: 5/8/2025
  Time: 6:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="bt9.User" %>
<%@ page import="bt9.UserManager" %>
<%
    UserManager userManager = (UserManager) request.getServletContext().getAttribute("userManager");
    List<User> users = (userManager != null) ? userManager.getUsers() : null;
    String message = (String) request.getAttribute("message");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Danh sách người dùng</title>
    <script>
        function confirmDelete(id) {
            if (confirm("Bạn có chắc chắn muốn xóa?")) {
                window.location.href = "../bt9?action=delete&id=" + id;
            }
        }
    </script>
</head>
<body>
<h2>Danh sách người dùng</h2>
<% if (message != null) { %>
<p style="color: green;"><%= message %></p>
<% } %>
<table border="1" cellpadding="8">
    <tr>
        <th>ID</th><th>Tên</th><th>Email</th><th>Hành động</th>
    </tr>
    <% if (users != null) {
        for (User user : users) { %>
    <tr>
        <td><%= user.getId() %></td>
        <td><%= user.getUsername() %></td>
        <td><%= user.getEmail() %></td>
        <td><button onclick="confirmDelete(<%= user.getId() %>)">Xóa</button></td>
    </tr>
    <%  } } %>
</table>
<br>
<a href="register.jsp">Quay lại trang đăng ký</a>
</body>
</html>

