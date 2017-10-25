<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <link href="<c:url value="/resources/mycss3.css" />" rel="stylesheet">
    <link rel="shortcut icon" href="../../resources/open-book.png">
    <title>BOOK</title>

</head>

<body>

    <h1 class="header" align="center">BOOK</h1>

    <table class="tg" align="center" width="96%">
    <tr>
        <th width=5%>ID</th>
        <th width=15%>Title</th>
        <th width=25%>Description</th>
        <th width=15%>Author</th>
        <th width=15%>ISBN</th>
        <th width=5%>PrintYear</th>
        <th width=5%>Read</th>
        <th width=5%>ReadAlready</th>
        <th width=5%>Update</th>
        <th width=5%>Delete</th>
    </tr>

    <tr height="10px" bgcolor="#eeeeee"/>

        <tr>
            <td align="center">${book.id}</td>
            <td align="center">${book.title}</td>
            <td align="center">${book.description}</td>
            <td>${book.author}</td>
            <td align="center">${book.isbn}</td>
            <td align="center">${book.printYear}</td>
            <c:url value="/read/${book.id}" var="read">
                <c:param name="page" value="${page+0}"/>
                <c:param name="stay" value="${1}"/>
            </c:url>
            <td align="center"><a href="<c:out value="${read}"/>"><img src="/resources/man-reading.png"></a></td>
            <td align="center"><c:if test="${book.readAlready}"><img src="/resources/draw-check-mark.png"></c:if></td>
            <c:url value="/update/${book.id}" var="update">
                <c:param name="page" value="${page+0}"/>
                <c:param name="stay" value="${1}"/>
            </c:url>
            <td align="center"><a href="<c:out value="${update}"/>"><img src="/resources/pencil-edit-button.png"></a></td>
            <c:url value="/delete/${book.id}" var="delete">
                <c:param name="page" value="${page+0}"/>
                <c:param name="stay" value="${1}"/>
            </c:url>
            <td align="center"><a href="<c:out value="${delete}"/>"><img src="/resources/delete.png"></a></td>
        </tr>

    </table>

    <br/>
    <br/>
    <a href="<c:url value="/?page=${page}"/>" class="link2">Back to books list</a>

</body>
</html>
