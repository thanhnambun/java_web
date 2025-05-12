<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head><meta charset="UTF-8"><title>Thêm sách</title></head>
<body>
<h1>Thêm sách mới</h1>
<form action="books" method="post">
    <input type="hidden" name="action" value="create"/>
    Mã sách: <input name="bookCode"/><br/>
    Tiêu đề: <input name="title"/><br/>
    Tác giả: <input name="author"/><br/>
    Thể loại: <input name="category"/><br/>
    Số lượng: <input name="quantity" type="number"/><br/>
    <button type="submit">Lưu</button>
</form>
<a href="books">Back to list</a>
</body>
</html>