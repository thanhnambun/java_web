<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Kết quả Upload</title>
</head>
<body>
<h2>${message}</h2>
<p>Tiêu đề: ${document.title}</p>
<p>Mô tả: ${document.description}</p>
<p>Đường dẫn file: ${filePath}</p>

<a href="/document/upload">Upload tài liệu khác</a>
</body>
</html>