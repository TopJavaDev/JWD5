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
<form method="get" action="parseXml">
    <c:choose>
        <c:when test="${maxPages <= 5}">
        <a href="parseXml?pageNumber=${pageNumber - 1 > 0 ? pageNumber - 1 : 1}">
            &lt;prev
        </a>&nbsp
        <c:forEach var="page" begin="1" end="${maxPages}">
            <a href="parseXml?pageNumber=${page}" class="pages">
                <c:out value="${page}"/>
            </a>&nbsp
        </c:forEach>
        <a href="parseXml?pageNumber=${pageNumber + 1 < maxPages ? pageNumber + 1 : maxPages}">
            next&gt;
        </a>
        </c:when>

        <c:when test="${maxPages > 5}">
        <a href="parseXml?pageNumber=${pageNumber - 1 > 0 ? pageNumber - 1 : 1}">
            &lt;prev
        </a>&nbsp
        <c:forEach var="page" begin="1" end="2">
            <a href="parseXml?pageNumber=${page}">
                <c:out value="${page}"/>
            </a>&nbsp
        </c:forEach>

        <input type="number" min="1" max="${maxPages}" name="pageNumber" value="${pageNumber}">&nbsp

        <c:forEach var="page" begin="${maxPages - 1}" end="${maxPages}">
            <a href="parseXml?pageNumber=${page}">
                <c:out value="${page}"/>
            </a>&nbsp
        </c:forEach>
        <a href="parseXml?pageNumber=${pageNumber + 1 < maxPages ? pageNumber + 1 : maxPages}">
            next&gt;
        </a><br>
        <input type="submit">
        </c:when>
    </c:choose>
    <br>
</form>
</p>
<p>
    <c:out value="${error}"/>
</p>
<script>
    document.getElementsByClassName("pages").item(${pageNumber - 1}).style.fontWeight = "bold";
</script>
</body>
</html>
