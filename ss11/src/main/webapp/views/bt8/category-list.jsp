<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Danh sách Danh mục</title></head>
<body>
<a href="${pageContext.request.contextPath}/categories/add">Thêm mới</a>
<table border="1">
  <tr>
    <th>ID</th>
    <th>Tên danh mục</th>
    <th>Trạng thái</th>
    <th>Hành động</th>
  </tr>
  <c:forEach var="cat" items="${categories}">
    <tr>
      <td>${cat.id}</td>
      <td>${cat.categoryName}</td>
      <td>${cat.status ? 'Kích hoạt' : 'Ẩn'}</td>
      <td>
        <a href="/categories/edit/${cat.id}">Sửa</a>
        <a href="/categories/delete/${cat.id}" onclick="return confirm('Bạn chắc chắn muốn xóa?')">Xóa</a>
      </td>
    </tr>
  </c:forEach>
</table>
</body>
</html>