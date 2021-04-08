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
                <div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">

                    <div class="row">
                        <div class="col-sm-12 col-md-6">
                            <div class="dataTables_length" id="dataTable_length"><label>Pokaż
                                <select
                                        name="dataTable_length" aria-controls="dataTable"
                                        class="custom-select custom-select-sm form-control form-control-sm">
                                    <option value="10">10 wyników</option>
                                    <option value="25">25 wyników</option>
                                    <option value="50">50 wyników</option>
                                    <option value="100">100 wyników</option>

                                </select>
                            </label>
                            </div>
                        </div>
                        <div class="col-sm-12 col-md-6">

                            <div id="dataTable_filter" class="dataTables_filter"
                                 style="position: absolute;  right: 0;width: 250px;">
                                <label>Szukaj:<input type="search"
                                                     class="form-control form-control-sm"
                                                     placeholder=""
                                                     aria-controls="dataTable"></label>
                            </div>
                        </div>
                    </div>


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
                                    NIP</th>
                                <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1"
                                    aria-label="Salary: activate to sort column ascending" style="width: 250px;">
                                    Akcje</th>
                            </thead>
                            <tfoot>
                            <tr>
                                <th rowspan="1" colspan="1">Nazwa użytkownika</th>
                                <th rowspan="1" colspan="1">NIP</th>
                                <th rowspan="1" colspan="1">Akcje</th>
                            </tr>
                            </tfoot>
                            <tbody>
                            <c:forEach items="${notifications}" var="notification">
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
    </div>
</div>
<%@ include file="../footer.jsp" %>
