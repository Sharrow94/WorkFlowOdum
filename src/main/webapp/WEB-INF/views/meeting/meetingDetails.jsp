<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Dodaj dane o płatności za zamówienie:</title>
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
                        <div class="text-center">
                            <h1 class="h4 text-gray-900 mb-4">Spotkanie o id: ${meeting.id}</h1>
                        </div>
                        <div class="col-sm-12">
                            <table class="table table-bordered dataTable text-gray-900" id="dataTable" width="100%"
                                   cellspacing="0"
                                   role="grid" aria-describedby="dataTable_info" style="width: 100%;">
                                <tbody>
                                <tr role="row" class="odd">
                                    <td>Nazwa</td>
                                    <td>${meeting.client.name}</td>
                                </tr>
                                <tr role="row" class="odd">
                                    <td>Data Spotkania</td>
                                    <td>${meeting.dateOfMeeting}</td>
                                </tr>
                                <tr role="row" class="odd">
                                    <td>Pracownik</td>
                                    <td>${meeting.user.firstName} ${meeting.user.lastName}</td>
                                </tr>
                                <tr role="row" class="odd">
                                    <td>Liczba notatek</td>
                                    <td>${meeting.countOfDocs}</td>
                                </tr>


                                </tbody>
                            </table>
                        </div>
                        <%--                        <c:if test="${meeting.countOfDocs>0}">--%>
                        <form:form method="post" encType="multipart/form-data">
                        <div class="card card-collapsable shadow">
                            <a class="card-header" href="#collapseCardMeal" data-toggle="collapse"
                               role="button" aria-expanded="false" aria-controls="collapseCardMeal">
                                        <span class="text-gray-900">
                                                <div class="row">
                                                    <div class="col-md-4">
                                                        Załączone pliki
                                                    </div>
                                                    <div class="col-md-4">
                                                        Data dodania
                                                    </div>
                                                    <div class="col-md-4">

                                                    </div>
                                                </div>
                                        </span>

                                <div class="card-collapsable-arrow">
                                    <i class="fas fa-chevron-down" style="color: #333333;"></i>
                                </div>
                            </a>
                            <div class="collapse" id="collapseCardMeal">
                                <div class="card-body">
                                    <c:forEach items="${meeting.doc}" var="d">
                                        <div class="form-group row">
                                            <div class="col-sm-4 mb-3 mb-sm-0">
                                                    ${d.docName}
                                            </div>
                                            <div class="col-sm-4 mb-3 mb-sm-0">
                                                    ${d.dateOfAdding}
                                            </div>
                                            <div class="col-sm-4 mb-3 mb-sm-0">
                                                <sec:authorize access="hasRole('ADMIN')">
                                                <a class="btn btn-primary btn-sm"
                                                   href="/app/meeting/${meeting.id}/send-attachment/${d.uuid}">Wyślij</a>
                                                </sec:authorize>
                                            </div>
                                        </div>
                                    </c:forEach>
                                    <div class="form-group row">
                                        <div class="col-sm-10 mb-3 mb-sm-0">
                                            <input class="btn btn-primary btn-user btn-block" value="Załącz pliki"
                                                   type="file" name="files" multiple required/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br><br>
                        <div class="row">
                            <div class="col-md-6">
                            </div>
                            <div class="col-md-6">
                                <input type="submit"
                                       class="btn btn-primary btn-user btn-block"/>
                            </div>
                            </form:form>

                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <a href="/app/meeting/all/${currentUser.id}" class="btn btn-primary btn-user btn-block">Powrót</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<%@ include file="../footer.jsp" %>
</html>
