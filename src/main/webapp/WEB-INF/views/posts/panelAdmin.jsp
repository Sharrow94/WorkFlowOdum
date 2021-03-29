<%@ include file="../header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container-fluid">
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Lista spotkań</h6>
            <a href='<c:url value="/admin/post/add"/>'
               class="btn btn-primary"
               style="background-color:#81994D; color:#3a3b45;position: absolute;  right: 8%;width: 170px;margin:-25px; border: 10px #f6c23e;">
                Utwórz informacje</a>
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
                                    Tytuł</th>
                                <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1"
                                    aria-label="Position: activate to sort column ascending" style="width: 160px;">
                                    Informacja</th>
                                <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1"
                                    aria-label="Office: activate to sort column ascending" style="width: 132px;">
                                    Data spotkania</th>
                                <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1"
                                    aria-label="Salary: activate to sort column ascending" style="width: 250px;">
                                    Akcje</th>
                            </thead>
                            <tfoot>
                            <tr>
                                <th rowspan="1" colspan="1">Tytuł</th>
                                <th rowspan="1" colspan="1">Informacja</th>
                                <th rowspan="1" colspan="1">Data spotkania</th>
                                <th rowspan="1" colspan="1">Akcje</th>
                            </tr>
                            </tfoot>
                            <tbody>
                            <c:forEach items="${listPost}" var="post">
                                <tr role="row" class="odd">
                                    <td><c:out value="${post.title}"/></td>
                                    <td><c:out value="${post.description}"/></td>
                                    <td><c:out value="${post.dateOfPost}"/></td>
                                    <td nowrap="nowrap">


                                        <a href='<c:url value="/admin/post/edit/${post.id}"/>'
                                           class="btn btn-primary"
                                           style="background-color:#81994D; border-color:#81994D;color:#3a3b45">Edytuj</a>
                                        <a href='<c:url value="/admin/post/delete/${post.id}"/>'
                                           class="btn btn-primary"
                                           style="background-color:#81994D; border-color:#81994D;color:#3a3b45">Usuń</a>
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