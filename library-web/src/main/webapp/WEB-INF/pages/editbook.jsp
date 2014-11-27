<%-- 
    Document   : book
    Created on : Nov 23, 2014, 11:18:36 PM
    Author     : michal
--%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<!doctype html>
<html>
  <body>
    <div class="wrapper">
      <%@ include file="header.jsp" %>
             <script>
        $(document).ready(function () {
$('#nav li:first ul').show();
});
    </script>
      <section>
        <h1><spring:message code="label.editbook"/></h1>
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
<form method="POST" action="${contextPath}/book/editpost">
  <table>
    <TR style="display: none;">
        <TD>Id:</TD>
        <TD><INPUT  TYPE="TEXT" NAME="idBook" value="${book.idBook}" SIZE="25"></TD>
    </TR>
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
    <Tr>
        <TD><spring:message code="label.genre"/>:</TD>
        <td>
            <select name="department">
              <option>Science</option>
              <option>Sport</option>
              <option>Autobiografy</option>
              <option>Religion</option>
            </select>
        </td>    
    </Tr>
    </table>    
    <input type="submit" value="Submit"/>
    </form>
      </section>
      <%@ include file="footer.jsp" %>
    </div>
    <script src="<c:url value="/resources/js/scale.fix.js" />"></script>
</body>
</html>
