
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Danh sách phim</title></head>
<body>
<a href="${pageContext.request.contextPath}/movies/add">Thêm phim mới</a>
<table border="1">
  <tr>
    <th>ID</th>
    <th>Tiêu đề</th>
    <th>Đạo diễn</th>
    <th>Ngày phát hành</th>
    <th>Thể loại</th>
    <th>Poster</th>
    <th>Hành động</th>
  </tr>
  <c:forEach var="movie" items="${movies}">
    <tr>
      <td>${movie.id}</td>
      <td>${movie.title}</td>
      <td>${movie.director}</td>
      <td>movie.releaseDate</td>
      <td>${movie.genre}</td>
      <td>
        <c:if test="${movie.poster != null}">
          <img src="${movie.poster}" width="100"/>
        </c:if>
      </td>
      <td>
        <a href="/movies/edit/${movie.id}">Sửa</a>
        <a href="/movies/delete/${movie.id}" onclick="return confirm('Bạn chắc chắn muốn xóa?')">Xóa</a>
      </td>
    </tr>
  </c:forEach>
</table>
</body>
</html>