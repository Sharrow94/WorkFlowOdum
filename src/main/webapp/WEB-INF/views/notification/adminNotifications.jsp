<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="../header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container-fluid">
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h4 class="m-0 font-weight-bold text-primary">Wszystkie powiadomienia</h4>

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
                                    Prowadzący spotkanie</th>
                                <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1"
                                    aria-label="Position: activate to sort column ascending" style="width: 160px;">
                                    Klient</th>
                                <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1"
                                    aria-label="Office: activate to sort column ascending" style="width: 132px;">
                                    Spotkanie</th>
                                <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1"
                                    aria-label="Office: activate to sort column ascending" style="width: 132px;">
                                    Treść</th>
                                <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1"
                                    aria-label="Salary: activate to sort column ascending" style="width: 250px;">
                                    Akcje</th>
                            </thead>
                            <tfoot>
                            <tr>
                                <th rowspan="1" colspan="1">Prowadzący spotkanie</th>
                                <th rowspan="1" colspan="1">Klient</th>
                                <th rowspan="1" colspan="1">Spotkanie</th>
                                <th rowspan="1" colspan="1">Treść</th>
                                <th rowspan="1" colspan="1">Akcje</th>
                            </tr>
                            </tfoot>
                            <tbody>
                            <c:forEach items="${notificationsForAdmin}" var="notificationForAdmin">
                                <tr role="row" class="odd">
                                    <td><c:out value="${notificationForAdmin.meeting.user.userName}"/></td>
                                    <td><c:out value="${notificationForAdmin.meeting.client.name}"/></td>
                                    <td><c:out value="${notificationForAdmin.meeting.id}"/></td>
                                    <td><c:out value="${notificationForAdmin.description}"/></td>
                                    <td nowrap="nowrap">

                                        <a href='/app/meeting/details/${notificationForAdmin.meeting.id}'
                                           class="btn btn-primary"
                                           style="background-color:#81994D; border-color:#81994D;color:#3a3b45">Szczegóły</a>
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
