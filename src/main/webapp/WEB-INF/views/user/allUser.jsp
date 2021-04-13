<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: robert
  Date: 05.03.2021
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="../header.jsp" %>

<div class="container-fluid">
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Lista użytkowników</h6>
            <a href='<c:url value="/admin/user/add"/>'
               class="btn btn-primary"
               style="background-color:#81994D; color:#3a3b45;position: absolute;  right: 8%;width: 170px;margin:-25px; border: 10px #f6c23e;">
                Dodaj użytkownika</a>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">

                    <div class="col-sm-12">
                        <table class="table table-bordered dataTable" id="dataTable" width="100%" cellspacing="0"
                               role="grid" aria-describedby="dataTable_info" style="width: 100%;">
                            <thead>

                            <tr role="row">
                                <th class="sorting_asc" tabindex="0" aria-controls="dataTable" rowspan="1"
                                    colspan="1" aria-sort="ascending"
                                    aria-label="Name: activate to sort column descending" style="width: 40px;">
                                    <spring:message code="app.id"/></th>
                                <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1"
                                    aria-label="Position: activate to sort column ascending" style="width: 150px;">
                                    <spring:message code="app.userName"/></th>
                                <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1"
                                    aria-label="Position: activate to sort column ascending" style="width: 150px;">
                                    <spring:message code="app.firstName"/></th>
                                <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1"
                                    aria-label="Position: activate to sort column ascending" style="width: 150px;">
                                    <spring:message code="app.lastName"/></th>
                                <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1"
                                    aria-label="Office: activate to sort column ascending" style="width: 120px;">
                                    <spring:message code="app.email"/></th>
<%--                                <sec:authorize access="hasAnyRole('ADMIN')">--%>
                                <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1"
                                    aria-label="Salary: activate to sort column ascending" style="width: 150px; ">
                                    <spring:message code="app.action"/></th>
<%--                                </sec:authorize>--%>
                            </thead>
                            <tfoot>

                            <tr>
                                <th rowspan="1" colspan="1"><spring:message code="app.id"/></th>
                                <th rowspan="1" colspan="1"><spring:message code="app.userName"/></th>
                                <th rowspan="1" colspan="1"><spring:message code="app.firstName"/></th>
                                <th rowspan="1" colspan="1"><spring:message code="app.lastName"/></th>
                                <th rowspan="1" colspan="1"><spring:message code="app.email"/></th>
<%--                                <sec:authorize access="hasRole('ADMIN')">--%>
                                    <th rowspan="1" colspan="1"><spring:message code="app.action"/></th>
<%--                                </sec:authorize>--%>
                            </tr>

                            </tfoot>
                            <tbody>
                            <c:forEach items="${user}" var="user">
                                <tr role="row" class="odd">
                                    <td><c:out value="${user.id}"/></td>
                                    <td><c:out value="${user.userName}"/></td>
                                    <td><c:out value="${user.firstName}"/></td>
                                    <td><c:out value="${user.lastName}"/></td>
                                    <td><c:out value="${user.email}"/></td>

<%--                                    <sec:authorize access="hasRole('ADMIN')">--%>


                                        <td>
                                            <a href='<c:url value="/admin/user/edit/${user.id}"/>'
                                               class="btn btn-primary"
                                               style="background-color:#81994D; border-color:#81994D;color:#3a3b45"><spring:message
                                                    code="app.edit"/></a>
                                            <a href='<c:url value="/admin/user/switch-enable/${user.id}"/>'
                                               class="btn btn-primary"
                                               style="background-color:#81994D; border-color:#81994D;color:#3a3b45"><spring:message
                                                    code="app.changeAuthorization"/></a>
                                            <a href='<c:url value="/app/meeting/all/${user.id}"/>'
                                               class="btn btn-primary"
                                               style="background-color:#81994D; border-color:#81994D;color:#3a3b45"><spring:message
                                                    code="app.meetings"/></a>

<%--                                            <a href='<c:url value="/admin/user/delete/${user.id}"/>'--%>
<%--                                               class="btn btn-primary"--%>
<%--                                               style="background-color:#FF0000; border-color:#FF0000;color:#ffffff"><spring:message--%>
<%--                                                    code="app.delete"/></a>--%>
                                        </td>
<%--                                    </sec:authorize>--%>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../footer.jsp" %>

</body>
</html>
