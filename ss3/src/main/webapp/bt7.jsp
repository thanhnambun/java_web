<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Đặt hàng</title>
</head>
<body>
<h2>Nhập thông tin đơn hàng</h2>
<form action="bt7" method="post">
    <table border="1">
        <tr>
            <th>Sản phẩm</th>
            <th>Số lượng</th>
            <th>Giá mỗi sản phẩm</th>
        </tr>
        <% for (int i = 0; i < 3; i++) { %>
        <tr>
            <td><input type="text" name="productName"></td>
            <td><input type="number" name="quantity" min="0"></td>
            <td><input type="number" step="0.01" name="price" min="0"></td>
        </tr>
        <% } %>
    </table>
    <br>
    <button type="submit">Tính tổng</button>
</form>
<h3>Tổng giá trị đơn hàng: ${total} VNĐ</h3>

</body>
</html>
