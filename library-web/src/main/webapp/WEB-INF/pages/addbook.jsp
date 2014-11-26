<%-- 
    Document   : book
    Created on : Nov 23, 2014, 11:18:36 PM
    Author     : michal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Add book</h1>
<form method="POST" action="addpost">
  <table>
    <TR>
        <TD>Book name:</TD>
        <TD><INPUT TYPE="TEXT" NAME="name" SIZE="25"></TD>
    </TR>
    <TR>
        <TD>Isbn:</TD>
        <TD><INPUT TYPE="TEXT" NAME="ISBN" SIZE="25"></TD>
    </TR>
    <TR>
        <TD>Authors:</TD>
        <TD><INPUT TYPE="TEXT" NAME="authors" SIZE="25"></TD>
    </TR>
    <TR>
        <TD>Description:</TD>
        <TD><INPUT TYPE="TEXT" NAME="description" SIZE="25"></TD>
    </TR>

  </table>    
  <input type="submit" value="Submit"/>
</form>
        
    </body>
</html>
