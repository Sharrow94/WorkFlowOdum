<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="../header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container-fluid">
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h4 class="m-0 font-weight-bold text-primary">Lista klientów</h4>
            <a href='<c:url value="/app/client/add"/>'
               class="btn btn-primary"
               style="background-color:#81994D; color:#3a3b45;position: absolute;  right: 8%;width: 170px;margin:-30px; border: 10px #f6c23e;">
                Dodaj klienta</a>
        </div>
        <div class="card-body">
            <div class="table-responsive">

                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                    <tr>
                        <th>Klient</th>
                        <th>NIP</th>
                        <th>Akcje</th>
                    </tr>
                    </thead>
                    <tfoot>
                    <tr>
                        <th>Klient</th>
                        <th>NIP</th>
                        <th>Akcje</th>
                    </tr>
                    </tfoot>
                    <tbody>
                    <c:forEach items="${clients}" var="client">
                        <tr role="row" class="odd">
                            <td><c:out value="${client.name}"/></td>
                            <td><c:out value="${client.nip}"/></td>
                            <td nowrap="nowrap">

                                <a href='<c:url value="/app/client/${client.id}"/>'
                                   class="btn btn-primary"
                                   style="background-color:#81994D; border-color:#81994D;color:#3a3b45">Szczegóły</a>
                                <a href='<c:url value="/app/client/edit/${client.id}"/>'
                                   class="btn btn-primary"
                                   style="background-color:#81994D; border-color:#81994D;color:#3a3b45">Edytuj</a>
                            </td>

                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<%@ include file="../footer.jsp" %>
