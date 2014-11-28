<%-- 
    Document   : membermanagement
    Created on : Nov 26, 2014, 14:43:08 AM
    Author     : Martin Malik
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
        <h2><spring:message code="label.membermanagement"/></h2>
        <ul>
            <li><a href="addformular"><spring:message code="label.addmember"/></a></li>
            <li><a href=""><spring:message code="label.editmember"/></a></li>
        </ul>
    </body>
</html>
