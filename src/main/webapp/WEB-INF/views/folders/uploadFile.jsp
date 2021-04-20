<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>File Upload / Download</title>
</head>
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
                        <div>
                            <div>
                                <h3>Edytuj pliki:</h3>
                            </div>
                            <form:form modelAttribute="docs" method="post" action="/edit/${docs.uuid}"
                                       encType="multipart/form-data">


                                <div class="align-items-stretch">
                                    <p>
                                        <input class="btn btn-primary btn-user btn-block" value="Załącz pliki"
                                               type="file"
                                               name="file"  required/>
                                    </p>
                                    <p>
                                        <button type="submit" class="btn btn-primary btn-user btn-block">Zatwierdź
                                        </button>
                                    </p>
                                </div>
                            </form:form>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="card-header py-3">
        <a href="/app/folders/${docs.client.id}/${docs.permit.id}" class="btn btn-primary"
           style="background-color:#81994D; color:#3a3b45;position: absolute;  right: 8%;width: 170px;margin:-25px; border: 10px #f6c23e;">
            Cofnij</a>
    </div>
</div>

</body>
<%@ include file="../footer.jsp" %>
</html>
