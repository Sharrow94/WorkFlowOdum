<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../header.jsp" %>
<head>
    <title>Home</title>
</head>

<style>
    .icon a {
        transition: all .1s ease-in;
    }

    .icon a:hover {
        transform: scale(1.05);
    }
</style>
<div class="container-fluid">
    <div class="mx-auto mt-md-5 col-md-6">
        <h1 class="h1 text-gray-700">Foldery poszczególnych klientów</h1>
    </div>
    <div class="container">
        <div class="row my-5">
            <c:forEach items="${clients}" var="client">
                <div class="col-md-3 icon">
                    <a href="/app/folders/${client.id}" class="d-block">
                        <i class="fas fa-folder-open fa-6x" style="color: #ffd700;"></i><br>
                    </a>
                    <span class="text-center">${client.name}</span>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<%@ include file="../footer.jsp" %>
