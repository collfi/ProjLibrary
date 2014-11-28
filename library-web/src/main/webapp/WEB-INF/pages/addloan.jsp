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
        You are going to add loan:
        <form method="POST" action="${contextPath}/loan/addloan">
            <INPUT TYPE="TEXT" NAME="bookid" value="${book.idBook}" hidden="true"/>
            <table>
              <TR>
                  <TD><INPUT TYPE="TEXT" NAME="pbookid" value="${pbook.idPrintedBook}"/></TD>
                  <TD><INPUT TYPE="TEXT" value="${book.name}"></TD>
                  <TD><INPUT TYPE="TEXT" name="datetill" class="date"/>DATE TILL</TD>
              </TR>
              <TR>
                  <select name="memberid">
                      <c:forEach var="listValue" items="${lmembers}">
                          <option value="${listValue.idMember}">${listValue.name}</option>
                      </c:forEach>
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

