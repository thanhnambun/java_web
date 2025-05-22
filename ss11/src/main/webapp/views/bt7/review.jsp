<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Đánh giá sản phẩm</title>
</head>
<body>
<h2>Đánh giá sản phẩm</h2>
<form:form method="POST" modelAttribute="productReview">
    <div>
        <label>Tên:</label>
        <form:input path="name"/>
        <form:errors path="name" cssClass="error"/>
    </div>
    <div>
        <label>Email:</label>
        <form:input path="email"/>
        <form:errors path="email" cssClass="error"/>
    </div>
    <div>
        <label>Đánh giá (1-5):</label>
        <form:input path="rating" type="number" min="1" max="5"/>
        <form:errors path="rating" cssClass="error"/>
    </div>
    <div>
        <label>Bình luận:</label>
        <form:textarea path="comment" rows="4" cols="40"/>
        <form:errors path="comment" cssClass="error"/>
    </div>
    <button type="submit">Gửi đánh giá</button>
</form:form>
</body>
</html>