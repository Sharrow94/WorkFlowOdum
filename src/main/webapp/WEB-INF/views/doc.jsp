<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>File Upload / Download</title>
</head>
<body>
<div>
    <h3>Upload Multiple Files</h3>
    <form:form modelAttribute="docs" method="post" action="/upload" encType="multipart/form-data">
        <input type="file" name="files" multiple required/>
        <select name="client">
            <c:forEach items="${clients}" var="client">
                <option value="${client.id}">
                        ${client.name}
                </option>
            </c:forEach>
        </select>
        <select name="permit">
            <c:forEach items="${permits}" var="permit">
                <option value="${permit.id}">
                        ${permit.type}
                </option>
            </c:forEach>
        </select>
        <button type="submit">Submit</button>
    </form:form>
</div>

<div>
    <h3>List of Documents</h3>
    <table>
        <thead>
        <tr>
            <th>Name</th>
            <th>Download Link</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${docs}" var="doc">
            <tr>
                <td>${doc.docName}</td>
                <td><a href="/download/${doc.uuid}">Download</a></td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>

</body>
</html>
