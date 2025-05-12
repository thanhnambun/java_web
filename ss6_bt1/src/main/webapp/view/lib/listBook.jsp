
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head><meta charset="UTF-8"><title>Danh sách sách</title></head>
<body>
<h1>Danh sách sách</h1>

<form action="books" method="get">
    Tìm theo tiêu đề/mã: <input name="keyword" value="${param.keyword}"/>
    <button type="submit">Search</button>
    <a href="books?action=add">Thêm mới</a>
</form>

<table border="1" cellpadding="5">
    <tr>
        <th>Mã sách</th><th>Tiêu đề</th><th>Tác giả</th>
        <th>Thể loại</th><th>Số lượng</th><th>Hành động</th>
    </tr>
    <c:forEach var="b" items="${books}">
        <tr>
            <td><c:out value="${b.bookCode}"/></td>
            <td><c:out value="${b.title}"/></td>
            <td><c:out value="${b.author}"/></td>
            <td><c:out value="${b.category}"/></td>
            <td><c:out value="${b.quantity}"/></td>
            <td>
                <a href="<c:url value='books?action=edit&amp;bookCode=${b.bookCode}'/>">Edit</a>
                <form action="books" method="post" style="display:inline">
                    <input type="hidden" name="action" value="delete"/>
                    <input type="hidden" name="bookCode" value="${b.bookCode}"/>
                    <button onclick="return confirm('Delete this book?')">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
