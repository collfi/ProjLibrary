<%-- 
    Document   : result
    Created on : Nov 24, 2014, 1:06:55 PM
    Author     : Boris Valentovic - xvalent2
--%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Spring MVC Form Handling</title>
</head>
<body>

<h2>Submitted Information</h2>
   <table>
    <tr>
        <td>Info:</td>
        
        <td>${book}</td>
        <td>${condition}</td>
        <td><%request.getParameter("state"); %>
        <td>${state}</td>

    </tr>
    
</table>
        <a href="show">Show </a>
</body>
</html>
