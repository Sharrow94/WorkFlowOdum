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
                        <c:set var="client" value="${meeting.client}"/>
                        <div class="col-sm-12 mt-4">
                            <form:form modelAttribute="clientEmployee" cssClass="form-group">
                            <table class="table table-bordered dataTable text-gray-900" id="dataTable" width="100%"
                                   cellspacing="0"
                                   role="grid" aria-describedby="dataTable_info" style="width: 100%;">
                                <tbody>
                                <tr role="row" class="odd">
                                    <td>Nazwa</td>
                                    <td>${client.name}</td>
                                </tr>
                                <tr role="row" class="odd">
                                    <td>Data Spotkania</td>
                                    <td>${meeting.dateOfMeeting}</td>
                                </tr>
                                <tr role="row" class="odd">
                                    <td>Nazwa dokumentu</td>
                                    <td>${doc.docName}</td>
                                </tr>
                                <tr role="row" class="odd">
                                    <td>Wybierz pracownika</td>
                                    <td><form:select path="id" items="${client.employees}" itemLabel="email" itemValue="id"
                                    cssClass="form-control"/> </td>
                                </tr>
                                </tbody>
                            </table>
                                <div class="row">
                                    <div class="col-md-6">
                                    </div>
                                    <div class="col-md-6">
                                        <input type="submit"
                                               class="btn btn-primary btn-user btn-block"/>
                                    </div>


                                </div>
                            </form:form>
                        </div>

                        </div>
                        <div class="row">
                            <div class="col-md-12 mb-3 pr-3">

                                <a href="/app/meeting/details/${meeting.id}"

                                   class="btn btn-primary btn-user btn-block">
                                    Powrót
                                </a>
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
