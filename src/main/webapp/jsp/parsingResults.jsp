<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style.css">
    <title>Result</title>
</head>
<body>
<table>
    <tr>
        <c:forEach var="book" items="${books}">
            <th>book - ${book.id}</th>
        </c:forEach>
    </tr>
    <tr>
        <c:forEach var="book" items="${books}">
            <td>${book.author}</td>
        </c:forEach>
    </tr>
    <tr>
        <c:forEach var="book" items="${books}">
            <td>${book.title}</td>
        </c:forEach>
    </tr>
    <tr>
        <c:forEach var="book" items="${books}">
            <td>${book.genre}</td>
        </c:forEach>
    </tr>
    <tr>
        <c:forEach var="book" items="${books}">
            <td>${book.price}</td>
        </c:forEach>
    </tr>
    <tr>
        <c:forEach var="book" items="${books}">
            <td><fmt:formatDate value="${book.publishDate}" pattern="dd MMM, yyyy"/></td>
        </c:forEach>
    </tr>
    <tr>
        <c:forEach var="book" items="${books}">
            <td>${book.description}</td>
        </c:forEach>
    </tr>
</table>
<p>
    <c:forEach var="page" begin="1" end="${maxPages}">
        <a href="parseXml?pageNumber=${page}">
            <c:out value="${page}"/>
        </a>
    </c:forEach>
</p>
</body>
</html>
