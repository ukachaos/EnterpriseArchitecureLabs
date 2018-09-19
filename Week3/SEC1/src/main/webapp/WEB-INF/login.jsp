<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
<h1>Login Page!</h1>
<c:if test="${error eq true}">
    <p>${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</p>
</c:if>
<form method="post" action="<c:url value='/login' />">
    User: <input name="username"
                 value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>'/>
    <br/>
    Pass: <input type="password" name='password'/> <br/>
    <input type="submit"/>
</form>
</body>
</html>