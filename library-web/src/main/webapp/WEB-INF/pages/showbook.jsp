<%-- 
    Document   : showbooks
    Created on : Nov 26, 2014, 12:12:50 AM
    Author     : michal
--%>
<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><spring:message code="label.book"/></h1>
        
        <p><spring:message code="label.name"/> : ${book.name}</p>
        <p>ISBN : ${book.ISBN}</p>
        <p><spring:message code="label.authors"/> : ${book.authors}</p>
        <p><spring:message code="label.description"/> : ${book.description}</p>

    </body>
</html>
