<%-- 
    Document   : showbooks
    Created on : Nov 26, 2014, 12:12:50 AM
    Author     : michal
--%>
<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%-- 
    Document   : showbooks
    Created on : Nov 26, 2014, 1:50:29 AM
    Author     : michal
--%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!doctype html>
<html>
  <body>
    <div class="wrapper">
      <%@ include file="header.jsp" %>
      <section>
        <h1><spring:message code="label.loan"/></h1>
        
    <table>
    <tbody>
    <tr>
        <td><spring:message code="label.name"/></td>
        <td>${name}</td>
    </tr>    

    <tr>
        <td>id</td>
        <td>${id}</td>
    </tr>    
    </tbody>
    </table>

      </section>
      <%@ include file="footer.jsp" %>
    </div>
    
    
    
    <script src="<c:url value="/resources/js/scale.fix.js" />"></script>
</body>
</html>