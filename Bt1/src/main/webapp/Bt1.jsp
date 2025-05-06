<%--
  Created by IntelliJ IDEA.
  User: nambu
  Date: 5/6/2025
  Time: 10:09 AM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Các thành phần hệ thống Web</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 20px;
        }
        h2 {
            text-align: center;
            color: #2c3e50;
        }
        table {
            width: 90%;
            margin: 20px auto;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #999;
            padding: 12px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .section {
            width: 90%;
            margin: 30px auto;
            line-height: 1.6;
        }
    </style>
</head>
<body>
<img src="images/1.png" alt="logo" width="700px">
<h2>1. Các thành phần chính</h2>
<table>
    <tr>
        <th>Thành phần</th>
        <th>Chức năng</th>
    </tr>
    <tr>
        <td>Client</td>
        <td>Thiết bị hoặc trình duyệt người dùng dùng để gửi yêu cầu và nhận kết quả từ hệ thống.</td>
    </tr>
    <tr>
        <td>Web Server</td>
        <td>Tiếp nhận yêu cầu HTTP từ Client, phản hồi tài nguyên tĩnh (HTML, CSS, JS) hoặc chuyển tiếp yêu cầu đến Application Server.</td>
    </tr>
    <tr>
        <td>Application Server</td>
        <td>Xử lý logic nghiệp vụ, truy vấn dữ liệu, và tạo nội dung động (thường qua JSP, Servlet, PHP, v.v.).</td>
    </tr>
    <tr>
        <td>Database</td>
        <td>Lưu trữ và quản lý dữ liệu ứng dụng (người dùng, sản phẩm, đơn hàng, v.v.). Được truy vấn bởi Application Server.</td>
    </tr>
</table>

<div class="section">
    <h2>2. Mối quan hệ và tương tác giữa các thành phần</h2>
    <p>
        Trong một hệ thống web điển hình, khi người dùng (Client) gửi yêu cầu qua trình duyệt (ví dụ: truy cập trang web), yêu cầu đó sẽ được gửi đến Web Server (ví dụ: Apache hoặc Nginx).
        Nếu đó là tài nguyên tĩnh như ảnh, HTML, CSS, Web Server sẽ phản hồi trực tiếp.
        Nếu yêu cầu cần xử lý logic nghiệp vụ, Web Server sẽ chuyển tiếp yêu cầu đến Application Server (ví dụ: Tomcat, Spring Boot),
        nơi xử lý mã nguồn như Servlet, JSP hoặc Controller.
    </p>
    <p>
        Trong quá trình xử lý, Application Server có thể gửi truy vấn đến Database (ví dụ: MySQL, PostgreSQL) để lấy hoặc lưu dữ liệu.
        Sau khi xử lý xong, kết quả được gửi lại từ Application Server → Web Server → Client để hiển thị cho người dùng.
    </p>
</div>

</body>
</html>
