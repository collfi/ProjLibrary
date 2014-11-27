<%-- 
    Document   : editpbook
    Created on : Nov 26, 2014, 4:36:30 PM
    Author     : Boris Valentovic - xvalent2
--%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<!doctype html>
<html>
  
  <body>
    <div class="wrapper">
      <%@ include file="header.jsp" %>
      <section>
        <h1><spring:message code="label.editpbookcondition"/></h1>
<form method="POST" action="${contextPath}/pbook/editpost">
  <table>
    <TR>
        <TD><INPUT TYPE="TEXT" NAME="idPrintedBook" value="${pbook.idPrintedBook}" hidden="true"></TD>
    </TR>
    <TR>
         <select name="condition">
            <option value="New"><spring:message code="label.new"/></option>
            <option value="Used"><spring:message code="label.used"/></option>
            <option value="Damaged"><spring:message code="label.damaged"/></option>
        </select>
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

