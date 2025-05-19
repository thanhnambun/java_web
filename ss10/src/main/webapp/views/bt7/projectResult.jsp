<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Kết Quả</title></head>
<body>
<h2>Dự án đã được tạo thành công!</h2>
<p><strong>Tên:</strong> ${project.name}</p>
<p><strong>Mô tả:</strong> ${project.description}</p>

<h3>Danh sách tài liệu:</h3>
<ul>
    <c:forEach items="${project.documents}" var="doc">
        <li>
            <a href="${doc.url}" target="_blank">${doc.name}</a>
        </li>
    </c:forEach>
</ul>

<br><a href="/projects/new">Tạo dự án mới</a>
</body>
</html>