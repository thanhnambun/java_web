<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Danh sách dự án</title></head>
<body>
<h2>Danh sách Dự Án</h2>
<ul>
  <c:forEach items="${projects}" var="p">
    <li>
      <strong>${p.name}</strong>: ${p.description}
      <ul>
        <c:forEach items="${p.documents}" var="d">
          <li><a href="${d.url}" target="_blank">${d.name}</a></li>
        </c:forEach>
      </ul>
    </li>
  </c:forEach>
</ul>
<a href="/projects/new">Tạo dự án mới</a>
</body>
</html>