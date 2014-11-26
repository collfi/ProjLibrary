<%-- 
    Document   : pbook
    Created on : Nov 24, 2014, 12:58:35 PM
    Author     : Boris Valentovic - xvalent2
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
                <a href="result">book</a>    
<form method="POST" action="add">
   <table>
    <tr>
        <td><form path="idPrintedBook">Id</form></td>
        
        <td><input id="aaa"name="aaa" value="432"/></td>
        
        <td>
            <select name="condition">
              <option>New</option>
              <option>Used</option>
              <option>Damaged</option>
            </select>
        </td>    
        <td>
            <select name="state">
              <option value="True">Loaned</option>
              <option value="False">Free</option>
            </select>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>    
</form>
        
    </body>
</html>
