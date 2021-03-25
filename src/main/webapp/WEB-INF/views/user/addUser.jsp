<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<spring:message code="app.login.registration" var="register"/>
<spring:message code="app.login.firstName" var="firstName"/>
<spring:message code="app.login.lastName" var="lastName"/>
<spring:message code="app.login.userName" var="userName"/>
<spring:message code="app.login.password" var="password"/>
<spring:message code="app.register.email" var="email"/>
<spring:message code="app.register.info" var="info"/>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>${register}</title>


<%--    <link href="<c:url value="/vendor/fontawesome-free/css/all.min.css"/>" rel="stylesheet" type="text/css">--%>
    <link
            href="<c:url value="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"/>"
            rel="stylesheet">

    <!-- Custom styles for this template-->
<%--    <link href="<c:url value="/css/sb-admin-2.min.css"/>" rel="stylesheet">--%>
</head>
<body class="bg-primary">
<div class="container">

    <div class="card o-hidden border-0 shadow-lg my-5">
        <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row">
                <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                <div class="col-lg-7">
                    <div class="p-5">
                        <form:form method="post" modelAttribute="user">
                            <div>
                                <h2>${register}</h2>

                                <div class="form-group row">

                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <form:input path="userName" placeholder="${userName}"
                                                    class="form-control form-control-user"/>
                                        <form:errors path="userName"/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <form:input path="firstName" placeholder="${firstName}"
                                                    class="form-control form-control-user"/>
                                        <form:errors path="firstName"/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <form:input path="lastName" placeholder="${lastName}"
                                                    class="form-control form-control-user"/>
                                        <form:errors path="lastName"/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <form:input path="email" placeholder="${email}"
                                                    class="form-control form-control-user"/>
                                        <form:errors path="email"/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <form:input path="password" placeholder="${password}"
                                                    class="form-control form-control-user"/>
                                        <form:errors path="password"/>
                                    </div>
                                </div>
                            </div>
                            <input type="submit" name="Save" class="btn btn-primary btn-user btn-block">

                        </form:form>
                        <div class="text-center">
                            <a class="small" href="<c:url value="/login"/>">${info}</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<!-- Bootstrap core JavaScript-->
<%--<script src="<c:url value="/vendor/jquery/jquery.min.js"/>"></script>--%>
<%--<script src="<c:url value="/vendor/bootstrap/js/bootstrap.bundle.min.js"/>"></script>--%>

<%--<!-- Core plugin JavaScript-->--%>
<%--<script src="<c:url value="/vendor/jquery-easing/jquery.easing.min.js"/>"></script>--%>

<%--<!-- Custom scripts for all pages-->--%>
<%--<script src="<c:url value="/js/sb-admin-2.min.js"/>"></script>--%>

</body>
</html>
