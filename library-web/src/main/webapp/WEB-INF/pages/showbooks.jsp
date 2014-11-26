<%-- 
    Document   : showbooks
    Created on : Nov 26, 2014, 1:50:29 AM
    Author     : michal
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pa165 Library</title>
    </head>
    <body>
        <h1>Show books!</h1>
            <c:forEach var="listValue" items="${list}">
                <li>${listValue.name}</li>
            </c:forEach>
    </body>
</html>
