<%--
  Created by IntelliJ IDEA.
  User: nambu
  Date: 5/8/2025
  Time: 5:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="bt8.Book" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Quản lý sách</title>
</head>
<body>
<h2>Thêm sách mới</h2>
<form action="bt8" method="post">
    Tên sách: <input type="text" name="title" required><br>
    Tác giả: <input type="text" name="author" required><br>
    Năm xuất bản: <input type="number" name="year" required><br>
    <button type="submit">Thêm</button>
</form>

<h2>Tìm kiếm sách</h2>
<form action="bt8" method="get">
    Tên sách: <input type="text" name="keyword">
    <button type="submit">Tìm</button>
</form>

<h2>Danh sách sách</h2>
<table border="1">
    <tr>
        <th>Tên sách</th>
        <th>Tác giả</th>
        <th>Năm xuất bản</th>
    </tr>
    <%
        List<Book> books = (List<Book>) request.getAttribute("listBook");
        if (books != null && !books.isEmpty()) {
            for (Book book : books) {
    %>
    <tr>
        <td><%= book.getNameBook() %></td>
        <td><%= book.getAuthorBook() %></td>
        <td><%= book.getYearBook() %></td>
    </tr>
    <%
        }
    } else {
    %>
    <tr><td colspan="3">Không có sách nào.</td></tr>
    <%
        }
    %>
</table>
</body>
</html>
