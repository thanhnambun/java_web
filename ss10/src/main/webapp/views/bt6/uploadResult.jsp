<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Kết quả Upload</title></head>
<body>
<h2>Upload thành công!</h2>
<p>Mô tả: ${description}</p>
<p>File URL: <a href="${fileUrl}" target="_blank">${fileUrl}</a></p>
<img src="${fileUrl}" alt="Uploaded file" width="300" />

<br/><a href="${pageContext.request.contextPath}/upload">Upload file khác</a>
</body>
</html>