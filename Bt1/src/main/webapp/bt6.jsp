<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Đăng ký vé xe</title>
</head>
<body>
<h2>Form Đăng Ký Vé Xe Cho Học Sinh</h2>

<form action="bt6" method="post">
    Họ và tên: <input type="text" name="fullName" required /><br/>
    Lớp: <input type="text" name="className" required /><br/>
    Loại xe: <input type="text" name="vehicleType" required /><br/>
    Biển số xe: <input type="text" name="licensePlate" required /><br/>
    <input type="submit" value="Đăng ký" />
</form>

<c:if test="${not empty message}">
    <p style="color:${status == 'success' ? 'green' : 'red'};">${message}</p>
</c:if>
</body>
</html>
