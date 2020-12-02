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
    <title>Prod IN ware list</title>
<h3>Prod total list</h3>
</head>
<body>
<c:forEach items="${products}" var="prod">

   <br> "${prod.name}"  "${prod.size}"   stock ${prodIn.get(prod)} ordered ${orders.get(prod)}

</c:forEach>
</body>
</html>
