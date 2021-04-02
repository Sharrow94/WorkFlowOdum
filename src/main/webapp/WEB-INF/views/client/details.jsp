<%--
  Created by IntelliJ IDEA.
  User: maciej
  Date: 29.03.2021
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<%@ include file="../header.jsp" %>
<body>
<div class="card o-hidden border-0 shadow-lg my-5">
    <div class="card-body p-0">
        <!-- Nested Row within Card Body -->
        <div class="row">
            <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
            <div class="col-lg-7">
                <div class="p-5">
                    <div class="text-center">
                        <h1 class="h4 text-gray-900 mb-4">Klient o id: ${client.id}</h1>
                    </div>
                    <div class="col-sm-12">
                        <table class="table table-bordered dataTable text-gray-900" id="dataTable" width="100%"
                               cellspacing="0"
                               role="grid" aria-describedby="dataTable_info" style="width: 100%;">
                            <tbody>
                            <tr role="row" class="odd">
                                <td>Nazwa</td>
                                <td>${client.name}</td>
                            </tr>
                            <tr role="row" class="odd">
                                <td>Email</td>
                                <td>${client.email}</td>
                            </tr>
                            <tr role="row" class="odd">
                                <td>NIP</td>
                                <td>${client.nip}</td>
                            </tr>
                            <tr role="row" class="odd">
                                <td>Ulica</td>
                                <td>${client.street}</td>
                            </tr>
                            <tr role="row" class="odd">
                                <td>Numer lokalu</td>
                                <td>${client.streetNr}</td>
                            </tr>
                            <tr role="row" class="odd">
                                <td>Miasto</td>
                                <td>${client.city}</td>
                            </tr>
                            <tr role="row" class="odd">
                                <td>Kod pocztowy</td>
                                <td>${client.zipCode}</td>
                            </tr>

                            </tbody>
                        </table>
                        <a href="/app/client/list" class="btn btn-primary btn-user btn-block">
                            Cofnij</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<%@ include file="../footer.jsp" %>
</html>
