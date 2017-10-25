<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<html>
<head>
    <meta charset="UTF-8">
    <link href="<c:url value="/resources/mycss2.css"/>" rel="stylesheet">
    <link rel="shortcut icon" href="../../resources/open-book.png">
    <title>Book Data</title>

</head>
<body>

<h1 class="header">
    <c:choose>
        <c:when test="${update}">
            UPDATE BOOK
        </c:when>
        <c:otherwise>
            CREATE NEW BOOK
        </c:otherwise>
    </c:choose>
</h1>

<c:url var="onaction" value="${action}"/>

<form:form method="POST" action="${onaction}" modelAttribute="book">

    <table  class="tg">

        <c:if test="${update}">
            <tr>
                <td><label class="font" for="id">ID: </label></td>
                <td>
                    <form:input class="field2" path="id" readonly="true" value="${book.id}"/>
                </td>
                <td></td>
            </tr>
        </c:if>

        <tr>
            <td><label class="font" for="title">Title: </label></td>
            <td><form:input class="field" path="title"/></td>
            <td><form:errors path="title" class="error"/></td>
        </tr>

        <tr>
            <td><label class="font" for="description">Description: </label></td>
            <td><form:input class="field" path="description"/></td>
            <td><form:errors path="description" class="error"/></td>
        </tr>

        <tr>
            <td><label class="font" for="isbn">ISBN: </label></td>
            <td><form:input class="field" path="isbn"/></td>
            <td><form:errors path="isbn" class="error"/></td>
        </tr>

        <tr>
            <td><label class="font" for="printYear">PrintYear: </label></td>
            <td><form:input class="field" path="printYear"/></td>
            <td><form:errors path="printYear" class="error"/></td>
        </tr>

        <tr>
            <td><label class="font" for="author">Author: </label></td>
            <c:choose>
                <c:when test="${update}">
                    <td><form:input class="field2" path="author" readonly="true" value="${book.author}"/>
                </c:when>
                <c:otherwise>
                    <td><form:input class="field" path="author"/></td>
                </c:otherwise>
            </c:choose>
            <td><form:errors path="author" class="error"/></td>
        </tr>

        <tr>
            <td colspan="3">
                <c:choose>
                    <c:when test="${update}">
                        <input class="btn" type="submit" value="Update Book"/>
                    </c:when>
                    <c:otherwise>
                        <input class="btn" type="submit" value="Create Book"/>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>

    </table>
</form:form>
<br/>
<br/>
<a href="<c:url value="/?page=${page+0}"/>" class="link">Back to books list</a>

</body>
</html>
