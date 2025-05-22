
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head><title>Thêm/Sửa Phim</title></head>
<body>
<form:form method="POST" modelAttribute="movie" enctype="multipart/form-data">
  <div>
    <label>Tiêu đề:</label>
    <form:input path="title"/>
    <form:errors path="title" cssClass="error"/>
  </div>
  <div>
    <label>Đạo diễn:</label>
    <form:input path="director"/>
    <form:errors path="director" cssClass="error"/>
  </div>
  <div>
    <label>Ngày phát hành:</label>
    <form:input path="releaseDate" type="date"/>
    <form:errors path="releaseDate" cssClass="error"/>
  </div>
  <div>
    <label>Thể loại:</label>
    <form:input path="genre"/>
    <form:errors path="genre" cssClass="error"/>
  </div>
  <div>
    <label>Poster:</label>
    <input type="file" name="posterFile"/>
    <c:if test="${movie.poster != null}">
      <img src="${movie.poster}" width="100"/>
    </c:if>
    <form:errors path="poster" cssClass="error"/>
    <span style="color:red">${uploadError}</span>
  </div>
  <button type="submit">Lưu</button>
</form:form>
<a href="${pageContext.request.contextPath}/movies">Quay lại danh sách</a>
</body>
</html>