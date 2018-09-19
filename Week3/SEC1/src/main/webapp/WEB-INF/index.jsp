<%--
  Created by IntelliJ IDEA.
  User: uka
  Date: 9/18/18
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container">
    <h1>This is secured!</h1>
    <p>
        Hello <b><c:out value="${pageContext.request.remoteUser}"/></b>
    </p>
</div>
</body>
</html>
