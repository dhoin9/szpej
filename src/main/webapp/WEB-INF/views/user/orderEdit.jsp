<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order Form</title>
</head>
<body>
<h1>Edit order</h1>


<form:form method="post" modelAttribute="order" >
    Type: <c:out value="${order.product.name}"></c:out>
    Size: <form:select path="product" items="${NameSize.get(order.product.name)}" itemLabel="size" itemValue="id"/> </br>
    Active <form:radiobutton path="active" value="true" label="YES"/>
    <form:radiobutton path="active" value="false" label="No"/></br>
    <form:hidden path="id" value="${order.id}"/>

    <form:hidden path="soldier.id" item="${soldier}" itemValue="id"/>
    <form:input path="quantity" value="${order.quantity}"/></br>
    <input type="submit" value="Save">

</form:form>

</body>
</html>
