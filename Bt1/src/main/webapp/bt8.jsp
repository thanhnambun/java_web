<%--
  Created by IntelliJ IDEA.
  User: nambu
  Date: 5/5/2025
  Time: 10:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.bt1.Task" %>
<%
    List<Task> tasks = (List<Task>) application.getAttribute("taskList");  // Lấy danh sách công việc từ ServletContext
%>

<!DOCTYPE html>
<html>
<head>
    <title>To-Do List</title>
</head>
<body>
<h2>Quản lý công việc</h2>

<!-- Form để thêm công việc -->
<form action="bt8" method="post">
    <input type="text" name="taskName" placeholder="Nhập công việc mới" required>
    <input type="hidden" name="action" value="add">
    <button type="submit">Thêm</button>
</form>

<h3>Danh sách công việc:</h3>
<ul>
    <%
        if (tasks != null && !tasks.isEmpty()) {
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
    %>
    <li>
        <form action="bt8" method="post" style="display:inline;">
            <input type="hidden" name="action" value="toggle">
            <input type="hidden" name="index" value="<%= i %>">
            <button type="submit"><%= task.isCompleted() ? "Hoàn thành" : " Chưa hoàn thành" %></button>
        </form>
        <span style="text-decoration:<%= task.isCompleted() ? "line-through" : "none" %>">
            <%= task.getName() %>
        </span>
    </li>
    <%
        }
    } else {
    %>
    <li>Chưa có công việc nào.</li>
    <%
        }
    %>
</ul>

</body>
</html>

