<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Prod in warehouse</title>
</head>
<body>
<h1>Edit Warehouse Stock</h1>


<form:form method="post" modelAttribute="prodInWar" >
   Name <th>${prodInWar.product.name}</th>
   Size <th>${prodInWar.product.size}</th>
    <br> id: ${prodInWar.id}
    <form:hidden path="id" value="${prodInWar.id}" />
    <form:hidden path="warehouse" value="${warehouse.id}" />
    <form:hidden path="product"  value="${prod.id}" />
   <br> Quantity <form:input path="quantity" value="${quantity}"/></br>
    <input type="submit" value="Save">

</form:form>

</body>
</html>
