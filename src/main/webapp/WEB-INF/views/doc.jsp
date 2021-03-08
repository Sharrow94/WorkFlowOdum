<%--
  Created by IntelliJ IDEA.
  User: maciej
  Date: 05.03.2021
  Time: 12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>File Upload / Download</title>
</head>
<body>
<div>
    <h3>Upload Multiple Files</h3>
    <form:form modelAttribute="docs" method="post" action="/uploadFiles" encType="multipart/form-data">
        <input type="file" name="files" multiple required />
        <button type="submit">Submit</button>
    </form:form>
</div>

<div>
    <h3>List of Documents</h3>
    <table>
        <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Download Link</th>
        </tr>
        </thead>
        <tbody>
        <C:forEach items="${docs}" var="doc">
            <tr>
                <td>${doc.id}</td>
                <td>${doc.docName}</td>
                <td><a href="/downloadFile/${doc.id}">Download</a></td>
            </tr>
        </C:forEach>

        </tbody>
    </table>
</div>

</body>
</html>