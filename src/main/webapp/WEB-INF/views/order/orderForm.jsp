<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order Form</title>
</head>
<body>
<h1>Create new order></h1>

    <form:form method="post" modelAttribute="order">
        <c:forEach items="${prod}" var="product">
            <c:out value="${product}"></c:out></br>
<%--        <form:select path="product.id" items="${products}" itemLabel="size"  itemValue="id"/></br--%>

        </c:forEach>
        <input type="submit" value="Save">
    </form:form>

</body>
</html>
