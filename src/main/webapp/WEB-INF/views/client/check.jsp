<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form:form method="post">
    <select name="clientId">
        <c:forEach items="${clients}" var="client">
            <option value="${client.id}">${client.name}</option>
        </c:forEach>
    </select>
    <input type="submit">
</form:form>

</body>
</html>
