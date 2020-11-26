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
    <title>Order list</title>
<h3>Orders list</h3>
</head>
<body>
<c:forEach items="${orders}" var="order">

    "${order}" <a href="/bookform/edit/${soldier.id}" >Edit</a>  <a href="/bookform/delete/${soldier.id}" >Delete</a></br>

</c:forEach>
</body>
</html>
