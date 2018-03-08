<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Result</title>
</head>
<body>
    <table>
        <c:forEach var="book" items="${books}">
            <th>book - ${book.id}</th>
            <tr>
                <td>${book.author}</td>
                <td>${book.title}</td>
            </tr>
        </c:forEach>

    </table>
</body>
</html>
