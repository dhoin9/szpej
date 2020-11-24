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
    <title>Book list</title>
<c:forEach items="${soldiers}" var="soldier">
    "${soldier}" <a href="/bookform/edit/${soldier.id}" >Edit</a>  <a href="/bookform/delete/${soldier.id}" >Delete</a></br>

</c:forEach>
</head>
<body>

</body>
</html>
