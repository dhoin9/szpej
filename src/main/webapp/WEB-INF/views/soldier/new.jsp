<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Soldier Form</title>
</head>
<body>
<h1>Create new Soldier</h1>

    <form:form method="post" modelAttribute="soldier">
        Fist Name  <form:input path="firstName"/>
        Last Name  <form:input path="lastName"/>
        Email  <form:input path="email"/>
        Password  <form:input path="password"/>
        Warehouse  <form:select path="warehouse.id" items="${warehouses}" itemLabel="name"  itemValue="id"/>
        <input type="submit" value="Save">
    </form:form>

</body>
</html>
