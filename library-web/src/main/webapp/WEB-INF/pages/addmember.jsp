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
$('#nav li:third ul').show();
});
    </script>
      <section>
        <h1><spring:message code="label.addmember"/></h1>  
          <c:choose>
        <c:when test="${error == 'missing'}">
            <p><font color="red"><spring:message code="label.validationmissing"/></font></p>
        </c:when>
    </c:choose>
    <c:choose>
        <c:when test="${error == 'duplicate'}">
            <p><font color="red"><spring:message code="label.validationduplicate"/></font></p>
        </c:when>
    </c:choose>
    <c:choose>
         <c:when test="${error == 'email'}">
            <p><font color="red"><spring:message code="label.validationemail"/></font></p>
            </c:when>
    </c:choose>
    <form method="POST" action="addpost">    
        <table>
            <TR>
                <TD><spring:message code="label.name"/>:</TD>
                <TD><INPUT TYPE="TEXT" NAME="name" SIZE="25" value=""></TD>
            </TR>
            <TR>
            <TD><spring:message code="label.email"/>:</TD>
            <TD><INPUT TYPE="TEXT" NAME="email" SIZE="25" value=""></TD>
            </TR>
            <TR>
                <TD><spring:message code="label.address"/>:</TD>
                <TD><INPUT TYPE="TEXT" NAME="address" SIZE="25" value=""></TD>
            </TR>
        </table>    
        
        <input type="submit" value="Submit"/>
        </form>
       
    </section>
    <%@ include file="footer.jsp" %>
    </div>
    <script src="<c:url value="/resources/js/scale.fix.js" />"></script>
</body>
</html>           