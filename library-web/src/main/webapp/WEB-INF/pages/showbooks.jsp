<%-- 
    Document   : showbooks
    Created on : Nov 26, 2014, 1:50:29 AM
    Author     : michal
--%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pa165 Library</title>
    </head>
    <body>
        <h1>All books!</h1>
        <table>
            <tr>
                <td><spring:message code="label.name"/></td><td>ISBN</td><td><spring:message code="label.authors"/></td><td><spring:message code="label.show"/>/<spring:message code="label.edit"/></td>
            </tr>
            <c:forEach var="listValue" items="${list}">
                <tr>
                    <td>${listValue.name}</td><td>${listValue.ISBN}</td><td>${listValue.authors}</td><td><a href="id/${listValue.idBook}">Show</a>/<a href="edit/${listValue.idBook}">Edit</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
