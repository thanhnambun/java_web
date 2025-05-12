
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head><meta charset="UTF-8"><title>Chỉnh sửa sách</title></head>
<body>
<h1>Chỉnh sửa sách</h1>
<form action="books" method="post">
    <input type="hidden" name="action" value="update"/>
    Mã sách:
    <input name="bookCode" readonly value="${book.bookCode}"/><br/>
    Tiêu đề: <input name="title"     value="${book.title}"/><br/>
    Tác giả: <input name="author"    value="${book.author}"/><br/>
    Thể loại: <input name="category" value="${book.category}"/><br/>
    Số lượng: <input name="quantity" type="number" value="${book.quantity}"/><br/>
    <button type="submit">Cập nhật</button>
</form>
<a href="books">Back to list</a>
</body>
</html>
