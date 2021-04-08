<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: robert
  Date: 04.03.2021
  Time: 19:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../header.jsp"%>
    <header class="page-header page-header-compact page-header-light border-bottom bg-white mb-4">
        <div class="container-fluid">
            <div class="page-header-content">
                <div class="row align-items-center justify-content-between pt-3">
                    <div class="col-auto mb-3">
                        <h1 class="page-header-title">
                            <spring:message code="app.editProfile"/>
                            <p>${currentUser.firstName} ${currentUser.lastName}</p>
                        </h1>
                    </div>
                </div>
            </div>
        </div>
    </header>
</head>
<body>

<div class="container mt-4">
    <!-- Account page navigation-->
        <nav class="nav nav-borders">
            <a class="nav-link active ml-0" href="<c:url value="/app/user/edit"/>"><spring:message code="app.profile"/></a>
            <a class="nav-link" href="<c:url value="/app/user/security"/>"><spring:message code="app.password"/></a>
        </nav>
        <hr class="mt-0 mb-4">
    <div class="row">
        <div class="col-xl-8">
            <!-- Account details card-->
            <div class="card mb-4">
                <div class="card-header"> <spring:message code="app.editProfile"/></div>
                <div class="card-body">
                    <form:form method="post" modelAttribute="users" action="/app/user/edit">
                        <!-- Form Group (username)-->
                        <form:hidden path="id"/>
                        <form:hidden path="password"/>
                        <form:hidden path="roles"/>
                        <div class="form-group">
                            <td><spring:message code="app.userName"/></td>
                            <form:input path="userName" class="form-control"/>
                            <form:errors path="userName"/>
                        </div>
                        <div class="form-group">
                            <td><spring:message code="app.firstName"/></td>
                            <form:input path="firstName" class="form-control"/>
                            <form:errors path="firstName"/>
                        </div>
                        <div class="form-group">
                            <td><spring:message code="app.lastName"/></td>
                            <form:input path="lastName" class="form-control"/>
                            <form:errors path="lastName"/>
                        </div>

                        <div class="form-group">
                            <td><spring:message code="app.email"/></td>
                            <form:input path="email" class="form-control "/>
                            <form:errors path="email"/>
                        </div>
                        <div class="form-group">
                            <td><spring:message code="app.phoneNumber"/></td>
                            <form:input path="phoneNumber" class="form-control "/>
                            <form:errors path="phoneNumber"/>
                        </div>

                        <button type="submit" class="btn btn-primary btn-user btn-block">
                            <spring:message code="app.edit"/></button>
                        <a href="/admin/user/all" class="btn btn-primary btn-user btn-block">
                            Cofnij</a>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>


<%@include file="../footer.jsp"%>
</body>
</html>