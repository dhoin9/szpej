<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Equipment Form</title>
</head>
<body>
<h1>Move equipment</h1>


<form:form method="post" modelAttribute="equipmentPasses">
    <c:forEach items="${NameSize}" var="map" varStatus="status">
        <%--        <c:set var="count" value="${count + 1}" scope="page"/>--%>
        Type: <c:out value="${map.key}"></c:out>
<%--        Size: <form:select path="product.id" items="${map.value}" itemLabel="size" itemValue="id"/></br>--%>
        Size: <form:select path="equipmentPassList[${status.index}].product" items="${map.value}" itemLabel="size" itemValue="id"/>
        Quantity <form:input path="equipmentPassList[${status.index}].quantity" value="1"/></br>
        <form:hidden path="equipmentPassList[${status.index}].soldier" value="6" />
        <form:hidden path="equipmentPassList[${status.index}].warehouse.id" value="1"/>
    </c:forEach>
    <input type="submit" value="Save">

</form:form>

</body>
</html>
