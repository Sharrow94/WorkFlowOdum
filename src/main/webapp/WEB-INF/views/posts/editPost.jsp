<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: robert
  Date: 29.03.2021
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="../header.jsp" %>
<body>
<div class="container">

    <div class="card o-hidden border-0 shadow-lg my-5">
        <div class="card-body p-0">

            <div class="row">
                <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                <div class="col-lg-7">
                    <div class="p-5">
                        <form:form modelAttribute="editPost" action="/admin/post/edit/${editPost.id}" method="post">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">Edytuj informacje</h1>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-6 mb-3 mb-sm-0">
                                    <td>Tytuł informacji:</td>
                                    <form:input path="title"
                                                class="form-control form-control-user"/>

                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-6 mb-3 mb-sm-0">
                                    <td>Informacje:</td>
                                    <form:textarea path="description"
                                                   class="form-control form-control-user"/>

                                </div>
                            </div>

                            <button type="submit" class="btn btn-primary btn-user btn-block">
                                Edytuj</button>
                            <a href="/admin/post/list" class="btn btn-primary btn-user btn-block">
                                Cofnij</a>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<%@ include file="../footer.jsp" %>