<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order Form</title>
</head>
<body>
<h1>Create new order></h1>

    <form:form method="post" modelAttribute="book">
        ISBN: <form:input path="isbn"/>
        <form:errors path="isbn" style="color:red"/><br/>
        Title: <form:input path="title"/>
        <form:errors path="title" style="color:red"/><br/>
        Author <form:input path="author"/>
        <form:errors path="author" style="color:red"/><br/>
        Publisher <form:input path="publisher"/>
        <form:errors path="publisher" style="color:red"/><br/>

        <input type="submit" value="Save">
    </form:form>

</body>
</html>
