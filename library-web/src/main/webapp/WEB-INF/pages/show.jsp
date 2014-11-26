<%-- 
    Document   : show
    Created on : Nov 26, 2014, 12:39:12 AM
    Author     : Boris Valentovic - xvalent2
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
            <c:forEach var="listValue" items="${list}">
                <li>${listValue}</li>
            </c:forEach>

    </body>
</html>
