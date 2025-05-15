<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %><!DOCTYPE html>
<html>
<head>
  <title>Your Farm</title>
</head>
<body>
<h1>Your Farm</h1>

<c:if test="${param.error == 'no-seed'}">
  <p style="color: red;">You don't have enough seeds to plant!</p>
</c:if>

<div style="display: grid; grid-template-columns: repeat(5, 1fr); gap: 10px;">
  <c:forEach var="i" begin="1" end="20">
    <div style="border: 1px solid black; height: 100px; text-align: center;">
      <form action="${pageContext.request.contextPath}/bt8910/plantSeed" method="post">
        <input type="hidden" name="plotId" value="${i}">
        <c:choose>
          <c:when test="${empty farm[i - 1]}">
            <select name="seedId">
              <c:forEach var="ws" items="${warehouse}">
                <c:if test="${ws.quantity > 0}">
                  <option value="${ws.seed.id}">
                      ${ws.seed.seedsName} - ${ws.seed.price} (Available: ${ws.quantity})
                  </option>
                </c:if>
              </c:forEach>
            </select>
            <button type="submit">Plant Seed</button>
          </c:when>
          <c:otherwise>
            <p>Planted: ${farm[i - 1]}</p>
          </c:otherwise>
        </c:choose>
      </form>
    </div>
  </c:forEach>
</div>
<br>
<a href="${pageContext.request.contextPath}/bt8910/shop">Go to Shop</a>
<a href="${pageContext.request.contextPath}/bt8910/warehouse">Go to Warehouse</a>
</body>
<script>

</script>
</html>