<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>File Upload / Download</title>
</head>
<%@ include file="header.jsp" %>
<body>
<div class="container">

    <div class="card o-hidden border-0 shadow-lg my-5">
        <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row">
                <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                <div class="col-lg-7">
                    <div class="p-5">
                        <div>
                            <div>
                                <h3>Wrzuć pliki:</h3>
                                <form:form modelAttribute="docs" method="post" action="/upload"
                                           encType="multipart/form-data">
                            </div>

                            <div>
                                <div class="align-items-stretch">
                                    <p><h4>Klient:</h4></p>
                                    <p>
                                        <select name="client" class="form-control form-control-user">
                                            <c:forEach items="${clients}" var="client">
                                                <option value="${client.id}">
                                                        ${client.name}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </p>
                                </div>
                                <div>
                                    <div class="align-items-stretch">
                                        <p><h5>Folder: </h5></p>
                                        <p>
                                            <select name="permit" class="form-control form-control-user">
                                                <c:forEach items="${permits}" var="permit">
                                                    <option value="${permit.id}">
                                                            ${permit.type}
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </p>
                                    </div>

                                </div>

                            </div>
                            <div class="align-items-stretch">
                                <p>
                                    <input class="btn btn-primary btn-user btn-block" value="Załącz pliki" type="file"
                                           name="files" multiple required/>
                                </p>
                                <p>
                                    <button type="submit" class="btn btn-primary btn-user btn-block">Zatwierdź</button>
                                </p>
                            </div>
                            </form:form>
                        </div>


                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
<%@ include file="footer.jsp" %>
</html>
