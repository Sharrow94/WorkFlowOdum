<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<head>
    <title>Home</title>
</head>

<!-- End of Topbar -->
<style>
    .containerTwo {
        width: 90%;
        height: 70%;
        margin: 80px auto;
    }
</style>
<div class="containerTwo">
    <c:forEach items="${listPost}" var="post">
        <div class="col-lg-6 mx-lg-auto">
            <div class="card shadow mb-4">
                <!-- Card Header - Accordion -->
                <a href="#collapseCardExample${post.id}" class="d-block card-header py-3" data-toggle="collapse"
                   role="button" aria-expanded="true" aria-controls="collapseCardExample${post.id}">
                    <h6 class="m-0 font-weight-bold text-primary"><c:out value="${post.title}"/></h6>
                </a>
                <!-- Card Content - Collapse -->
                <div class="collapse show" id="collapseCardExample${post.id}" style="">
                    <div class="card-body">
                        <blockquote class="blockquote mb-0">
                            <p>
                                <c:out value="${post.description}"/>
                            </p>
                        </blockquote>
                        <blockquote class="blockquote mb-1 d-flex justify-content-end">
                            <c:forEach items="${post.docs}" var="doc">
                                <a href="/download/${doc.uuid}" class="btn-primary btn-sm my-2 mx-1">${doc.docName}</a>
                            </c:forEach>
                        </blockquote>
                        <footer class="blockquote-footer"><cite title="Source Title">${post.dateOfPost}</cite></footer>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

<%@ include file="footer.jsp" %>
