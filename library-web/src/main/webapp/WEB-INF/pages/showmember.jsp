<%-- 
    Document   : book
    Created on : Nov 23, 2014, 11:18:36 PM
    Author     : michal
--%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<!doctype html>
<html>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
  <body>
    <div class="wrapper">
      <%@ include file="header.jsp" %>
       <script>
        $(document).ready(function () {
$('#nav li:first ul').show();
});
    </script>
      <section>
        <h1><spring:message code="label.member"/></h1></br>
        
        <p><spring:message code="label.name"/> : ${member.name}</p>
        <p><spring:message code="label.email"/> : ${member.email}</p>
        <p><spring:message code="label.address"/> : ${member.address}</p>
        
      </section>      
      <%@ include file="footer.jsp" %>
    </div>
    <script src="<c:url value="/resources/js/scale.fix.js" />"></script>
</body>
</html>