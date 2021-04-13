<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="../header.jsp" %>
<header class="page-header page-header-compact page-header-light border-bottom bg-white mb-4">
    <div class="container-fluid">
        <div class="page-header-content">
            <div class="row align-items-center justify-content-between pt-3">
                <div class="col-auto mb-3">
                    <h1 class="page-header-title">
                        <spring:message code="app.changePassword"/>
                        <p>${currentUser.firstName} ${currentUser.lastName}</p>
                    </h1>
                </div>
            </div>
        </div>
    </div>
</header>

<body>
<div class="container mt-4">
    <!-- Account page navigation-->
    <nav class="nav nav-borders">
        <a class="nav-link active ml-0" href="<c:url value="/app/user/edit"/>"><spring:message code="app.profile"/></a>
        <a class="nav-link" href="<c:url value="/app/user/security"/>"><spring:message code="app.password"/></a>
    </nav>
    <hr class="mt-0 mb-4">
    <div class="row">
        <div class="col-lg-8">
            <!-- Change password card-->
            <div class="card mb-4">
                <div class="card-header"><spring:message code="app.changePassword"/>:</div>
                <div class="card-body">
                    <form:form method="post" action="/app/user/security">

                        <div class="form-group">
                            <input id="oldPassword" type="password" name="oldPassword"
                                   placeholder="Stare hasło" class="form-control ">
                        </div>

                        <div class="form-group">
                            <input id="newPassword" type="password" name="newPassword"
                                   placeholder="Nowe hasło" class="form-control ">
                        </div>

                        <div class="form-group">
                            <input id="confirmPassword" type="password" name="confirmPassword"
                                   placeholder="Powtórz hasło" class="form-control ">
                        </div>


                        <button type="submit" class="btn btn-primary">
                            <spring:message code="app.changePassword"/></button>

                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<%@ include file="../footer.jsp" %>
</html>