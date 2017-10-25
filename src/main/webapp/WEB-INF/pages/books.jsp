<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <link href="<c:url value="/resources/mycss.css" />" rel="stylesheet">
    <link rel="shortcut icon" href="../../resources/open-book.png">
    <title>BOOKS</title>

</head>

<body>

<c:if test="${!empty listBooks}">

    <h1 class="header" align="center">BOOKS LIST</h1>

    <table class="tg" align="center" width="96%">
        <tr>
            <th width=5%>ID</th>
            <th width=25%>Title</th>
            <th width=20%>Author</th>
            <th width=15%>ISBN</th>
            <th width=10%>PrintYear</th>
            <th width=10%>Read</th>
            <th width=5%>ReadAlready</th>
            <th width=5%>Update</th>
            <th width=5%>Delete</th>
        </tr>

        <tr height="10px" bgcolor="#eeeeee"/>

        <c:forEach items="${listBooks}" var="book">
            <tr>
                <td align="center">${book.id}</td>
                <c:url value="/book/${book.id}" var="showbook">
                    <c:param name="page" value="${page+0}"/>
                </c:url>
                <td align="center"><a href="<c:out value="${showbook}"/>" class="link3">${book.title}</a></td>
                <td>${book.author}</td>
                <td align="center">${book.isbn}</td>
                <td align="center">${book.printYear}</td>
                <c:url value="/read/${book.id}" var="read">
                    <c:param name="page" value="${page+0}"/>
                    <c:param name="stay" value="${stay}"/>
                </c:url>
                <td align="center"><a href="<c:out value="${read}"/>"><img src="/resources/man-reading.png"></a></td>
                <td align="center"><c:if test="${book.readAlready}"><img src="/resources/draw-check-mark.png"></c:if></td>
                <c:url value="/update/${book.id}" var="update">
                    <c:param name="page" value="${page+0}"/>
                    <c:param name="stay" value="${stay}"/>
                </c:url>
                <td align="center"><a href="<c:out value="${update}"/>"><img src="/resources/pencil-edit-button.png"></a></td>
                <c:url value="/delete/${book.id}" var="delete">
                    <c:param name="page" value="${page+0}"/>
                    <c:param name="stay" value="${stay}"/>
                </c:url>
                <td align="center"><a href="<c:out value="${delete}"/>"><img src="/resources/delete.png"></a></td>
            </tr>
        </c:forEach>
    </table>
    <table class="material" align="center">
        <tr align="center">
            <td align="center">
                <c:url value="${root}" var="prev">
                    <c:param name="page" value="${page-1}"/>
                </c:url>
                <c:if test="${page > 1}">
                    <a href="<c:out value="${prev}" />" class="link">Previous</a>
                </c:if>

                <c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
                    <c:choose>
                        <c:when test="${page == i.index}">
                            <span class="font3">${i.index}</span>
                        </c:when>
                        <c:otherwise>
                            <c:url value="${root}" var="url">
                                <c:param name="page" value="${i.index}"/>
                            </c:url>
                            <a href='<c:out value="${url}" />' class="link">${i.index}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:url value="${root}" var="next">
                    <c:param name="page" value="${page + 1}"/>
                </c:url>
                <c:if test="${page + 1 <= maxPages}">
                    <a href='<c:out value="${next}" />' class="link">Next</a>
                </c:if>
            </td>
        </tr>
    </table>


</c:if>

<c:if test="${empty listBooks}">
    <br/>
    <br/>
    <span class="font2">List of books is empty.</span>
</c:if>

<c:if test="${searchresults}">
    <a href="<c:url value="/"/>" class="link2">Back to books list</a>
</c:if>

<c:url var="searchbook" value="/searchresults"/>
<c:url var="createbook" value="/create">
    <c:param name="page" value="${page+0}"/>
</c:url>
<c:url var="unread" value="/searchnotread"/>

<table width="80%" class="form" align="center">
    <tr>
        <td>
            <table class="form" align="center">
                <form:form action="${searchbook}" modelAttribute="searchedbook">
                    <tr>
                        <td><input class="btn" type="submit" name="action" value="Search"/></td>
                        <td><form:input class="field" path="title"/> <label class="font" for="title">Title</label></td>
                    </tr>
                </form:form>
            </table>
        </td>
        <td>
            <table width="90%" class="form" align="center">
                <form:form action="${unread}">
                    <tr>
                        <td><input class="btn" type="submit" value="Search unread books"/></td>
                    </tr>
                </form:form>
            </table>
        </td>
        <td>
            <table width="90%" class="form" align="center">
                <form:form action="${createbook}">
                    <tr>
                        <td><input class="btn" type="submit" value="Create new book"/></td>
                    </tr>
                </form:form>
            </table>
        </td>
    </tr>
</table>

</body>
</html>
