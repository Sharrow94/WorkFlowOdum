<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<head>
    <title>Home</title>
</head>

            <!-- End of Topbar -->
<style>
    .containerTwo{
        width: 90%;
        height: 70%;
        margin: 80px auto;
    }
</style>
<div class="containerTwo">
<c:forEach items="${listPost}" var="post">
    <div class="card shadow mb-4">
        <!-- Card Header - Accordion -->
        <a href="#collapseCardExample" class="d-block card-header py-3" data-toggle="collapse" role="button" aria-expanded="true" aria-controls="collapseCardExample">
            <h6 class="m-0 font-weight-bold text-primary"><c:out value="${post.title}"/></h6>
            <h7 class="m-0 font-weight-bold text-primary"><c:out value="${post.dateOfPost}"/></h7>
        </a>
        <!-- Card Content - Collapse -->
        <div class="collapse show" id="collapseCardExample" style="">
            <div class="card-body">
                <c:out value="${post.description}"/>
            </div>
        </div>
    </div>
</c:forEach>
</div>

<%@ include file="footer.jsp" %>