<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="../header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style>
    .table-danger td{
        border-color: #e3e6f0;
    }
</style>
    <div class="container-fluid">
        <div class="card shadow mb-4">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">Lista spotkań</h6>
                <a href='<c:url value="/app/meeting/add"/>'
                   class="btn btn-primary"
                   style="background-color:#81994D; color:#3a3b45;position: absolute;  right: 8%;width: 170px;margin:-25px; border: 10px #f6c23e;">
                    Utwórz spotkanie</a>
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">

                        <div class="col-sm-12">
                            <table class="table table-bordered dataTable" id="dataTable" width="100%" cellspacing="0"
                                   role="grid" aria-describedby="dataTable_info" style="width: 100%;">
                                <thead>
                                <tr role="row">
                                    <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1"
                                        aria-label="Position: activate to sort column ascending" style="width: 160px;">
                                        Klient</th>
                                    <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1"
                                        aria-label="Office: activate to sort column ascending" style="width: 132px;">
                                        Data spotkania</th>
                                    <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1"
                                        aria-label="Salary: activate to sort column ascending" style="width: 250px;">
                                        Akcje</th>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th rowspan="1" colspan="1">Klient</th>
                                    <th rowspan="1" colspan="1">Data spotkania</th>
                                    <th rowspan="1" colspan="1">Akcje</th>
                                </tr>
                                </tfoot>
                                <tbody>
                                <c:forEach items="${meetings}" var="meeting">
                                    <c:if test="${meeting.countOfDocs==0}">
                                        <tr role="row" class="odd table-danger" style="border-color: #e3e6f0;">
                                    </c:if>
                                    <c:if test="${meeting.countOfDocs!=0}">
                                        <tr role="row" class="odd">
                                    </c:if>
                                        <td><c:out value="${meeting.client.name}"/></td>
                                        <td><c:out value="${meeting.dateOfMeeting}"/></td>
                                        <td nowrap="nowrap">

                                            <a href='<c:url value="/app/meeting/details/${meeting.id}"/>'
                                               class="btn btn-primary"
                                               style="background-color:#81994D; border-color:#81994D;color:#3a3b45">Szczegóły</a>
                                            <a href='<c:url value="/app/meeting/edit/${meeting.id}"/>'
                                               class="btn btn-primary"
                                               style="background-color:#81994D; border-color:#81994D;color:#3a3b45">Edytuj</a>
                                            <a href="/app/client/${meeting.client.id}/meeting/download/merged/pdf"
                                               class="btn btn-primary">
                                                Pobierz scalony .pdf
                                            </a>
                                            <a href="/app/client/${meeting.client.id}/meeting/download/merged/docx"
                                               class="btn btn-primary">
                                                Pobierz scalony .docx
                                            </a>
                                        </td>

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
