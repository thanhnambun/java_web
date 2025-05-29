
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="title" /></title>

</head>
<body>
<h2>${title}</h2>
<p>${greeting}</p>
<p>${instruction}</p>
<form action="${pageContext.request.contextPath}/B3/change-language" method="get">
    <div class="form-group">
        <label for="lang">${languageSelect}</label>
        <select id="lang" name="lang">
            <option value="en" <c:if test="${currentLanguage == 'en'}">selected</c:if>>${languageEnglish}</option>
            <option value="vi" <c:if test="${currentLanguage == 'vi'}">selected</c:if>>${languageVietnamese}</option>
        </select>
    </div>
    <button type="submit">${submit}</button>
</form>
</body>
</html>