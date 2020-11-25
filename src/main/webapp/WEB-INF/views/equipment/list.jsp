<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: john
  Date: 17.11.2020
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Soldier equipment list</title>
    First Name: <c:out value="${soldier.firstName}"></c:out>
    Last Name: <c:out value="${soldier.lastName}"></c:out></br>
</head>
<body>
<c:forEach items="${equipmentPass}" var="equipment">
    <c:out value="Name: ${equipment.product.name}"></c:out>
<c:out value="Size: ${equipment.product.size}"></c:out></br>

</c:forEach>
</body>
</html>
