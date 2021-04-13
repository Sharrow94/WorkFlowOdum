<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message code="app.add" var="add"/>

<html>
<head>
    <title>Dodaj pracownika dla firmy: ${clientName}</title>
</head>
<%@ include file="../header.jsp" %>
<body>
<div class="container">

    <div class="card o-hidden border-0 shadow-lg my-5">
        <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row">
                <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                <div class="col-lg-7">
                    <div class="p-5">
                        <form:form modelAttribute="employee" method="post" action="/app/client/${clientId}/employee/addEmployee">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">Dodaj pracownika</h1>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-6 mb-3 mb-sm-0">
                                    <td>Imię:</td>
                                    <div>
                                        <form:input path="firstName"/>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-6 mb-3 mb-sm-0">
                                    <td>Nazwisko:</td>
                                    <div>
                                        <form:input path="lastName"/>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-6 mb-3 mb-sm-0">
                                    <td>Email:</td>
                                    <div>
                                        <form:input path="email"/>

                                    </div>
                                </div>
                            </div>

                            <button type="submit" class="btn btn-primary btn-user btn-block">
                                    ${add}</button>
                            <a href="/app/client/${clientId}/employee" class="btn btn-primary btn-user btn-block">
                                Cofnij</a>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<%@ include file="../footer.jsp" %>
</html>
