<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Error page</title>
</head>
<body>
    <h3>ERROR:</h3>
    <h4>
        <c:out value="${requestScope.error}"/>
    </h4>
</body>
</html>
