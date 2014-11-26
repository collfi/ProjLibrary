<%-- 
    Document   : editbook
    Created on : Nov 26, 2014, 4:23:19 AM
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
        <h1><spring:message code="label.editbook"/></h1>
<form method="POST" action="addpost">
  <table>
    <TR>
        <TD><spring:message code="label.name"/>:</TD>
        <TD><INPUT TYPE="TEXT" NAME="name" value="${book.name}" SIZE="25"></TD>
    </TR>
    <TR>
        <TD>Isbn:</TD>
        <TD><INPUT TYPE="TEXT" NAME="ISBN" value="${book.ISBN}" SIZE="25"></TD>
    </TR>
    <TR>
        <TD><spring:message code="label.authors"/>:</TD>
        <TD><INPUT TYPE="TEXT" NAME="authors" value="${book.authors}" SIZE="25"></TD>
    </TR>
    <TR>
        <TD><spring:message code="label.description"/>:</TD>
        <TD><INPUT TYPE="TEXT" NAME="description" value="${book.description}" SIZE="25"></TD>
    </TR>

  </table>    
  <input type="submit" value="Submit"/>
</form>
    </body>
</html>
