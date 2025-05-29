<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title><spring:message code="register.title"/></title></head>
<body>
<h2><spring:message code="register.title"/></h2>
<form:form modelAttribute="user" method="post">
  <div>
    <spring:message code="username"/>:
    <form:input path="username"/>
    <form:errors path="username" cssClass="error"/>
  </div>
  <div>
    <spring:message code="password"/>:
    <form:password path="password"/>
    <form:errors path="password" cssClass="error"/>
  </div>
  <div>
    <spring:message code="confirmPassword"/>:
    <form:password path="confirmPassword"/>
    <form:errors path="confirmPassword" cssClass="error"/>
  </div>
  <div>
    <spring:message code="email"/>:
    <form:input path="email"/>
    <form:errors path="email" cssClass="error"/>
  </div>
  <button type="submit"><spring:message code="register.button"/></button>
</form:form>
</body>
</html>