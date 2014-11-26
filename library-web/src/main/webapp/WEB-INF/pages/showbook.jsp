<%-- 
    Document   : showbooks
    Created on : Nov 26, 2014, 12:12:50 AM
    Author     : michal
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>All books!</h1>
        
        <p>${name}</p>
        <ul>
            <c:forEach var="listValue" items="${list}">
                <li>${listValue}</li>
            </c:forEach>
        </ul>
    </body>
</html>
