<%-- 
    Document   : bookmanagement
    Created on : Nov 26, 2014, 2:43:08 AM
    Author     : michal
--%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pa165</title>
    </head>
    <body>
        <h2><spring:message code="label.bookmanagement"/></h2>
        <ul>
            <li><a href="addformular"><spring:message code="label.addbook"/></a></li>
            <li><a href=""><spring:message code="label.editbook"/></a></li>
            <li><a href="showbooks"><spring:message code="label.allbooks"/></a></li>
        </ul>
    </body>
</html>
