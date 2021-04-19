
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
                        <h1 class="h4 text-gray-900 mb-4">Dokument: ${docs.docName}</h1>
                    </div>
                    <div class="col-sm-12">
                        <table class="table table-bordered dataTable text-gray-900" id="dataTable" width="100%"
                               cellspacing="0"
                               role="grid" aria-describedby="dataTable_info" style="width: 100%;">
                            <tbody>
                            <tr role="row" class="odd">
                                <td>Nazwa pliku</td>
                                <td>${docs.docName}</td>
                            </tr>
                            <tr role="row" class="odd">
                                <td>Data dodania</td>
                                <td>${docs.dateOfAdding}</td>
                            </tr>
                            <tr role="row" class="odd">
                                <td>Czas dodania</td>
                                <td>${docs.timeOfAdding}</td>
                            </tr>
                            <tr role="row" class="odd">
                                <td>Id osoby dodającej</td>
                                <td>${docs.userAddingId}</td>
                            </tr>
                            <tr role="row" class="odd">
                                <td>Data ostatniej edycji</td>
                                <td>${docs.dateOfLastEdit}</td>
                            </tr>
                            <tr role="row" class="odd">
                                <td>Id osoby edytującej</td>
                                <td>${docs.userEditingId}</td>
                            </tr>
                            <tr role="row" class="odd">
                                <td>Permit</td>
                                <td>${docs.permit.type}</td>
                            </tr>
                            <tr role="row" class="odd">
                                <td>Klient</td>
                                <td>${docs.client.name}</td>
                            </tr>

                            </tbody>
                        </table>
                        <a href="/app/folders/${docs.client.id}/${docs.permit.id}" class="btn btn-primary btn-user btn-block">
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
