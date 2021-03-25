<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Dodaj klienta</title>
</head>
<body>

<h1>Dodaj klienta</h1>
<form:form modelAttribute="client" method="post">
    Nazwa: <form:input path="name"/><br>
    Email: <form:input path="email"/><br>
    NIP: <form:input path="nip"/><br>
    Nazwa ulicy: <form:input path="street"/><br>
    Nr budynku: <form:input path="streetNr"/><br>
    Miasto: <form:input path="city"/><br>
    Kod pocztowy: <form:input path="zipCode"/><br>
    <input type="submit" value="Dodaj">
</form:form>


</body>
</html>
