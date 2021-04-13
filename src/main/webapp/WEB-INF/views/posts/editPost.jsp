<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: robert
  Date: 29.03.2021
  Time: 15:21
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
            <!-- Nested Row within Card Body -->
            <div class="row">
                <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                <div class="col-lg-7">
                    <div class="p-5">
                        <form:form modelAttribute="editPost" action="/admin/post/edit" method="post"
                                   encType="multipart/form-data">
                            <form:hidden path="id"/>
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">Dodaj informacje</h1>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-6 mb-3 mb-sm-0">
                                    <td>Tytuł informacji:</td>
                                    <form:input path="title" placeholder="Nazwa"
                                                class="form-control form-control-user"/>

                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-6 mb-3 mb-sm-0">
                                    <td>Informacje:</td>
                                    <form:textarea path="description" placeholder="Informacje"
                                                   class="form-control form-control-user"/>

                                </div>
                            </div>
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
                                        <c:forEach items="${editPost.docs}" var="d">
                                            <div class="form-group row">
                                                <div class="col-sm-4 mb-3 mb-sm-0">
                                                        ${d.docName}
                                                </div>
                                                <div class="col-sm-8 mb-3 mb-sm-0">
                                                        ${d.dateOfAdding}
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
                            <div>
                                <button type="submit" class="btn btn-primary btn-user btn-block">Zatwierdź</button>
                            </div>
                        </form:form>
                        <a href="/admin/post/list" class="btn btn-primary btn-user btn-block">
                            Cofnij</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<%@ include file="../footer.jsp" %>
