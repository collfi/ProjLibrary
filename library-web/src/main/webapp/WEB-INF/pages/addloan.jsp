<%-- 
    Document   : addloan
    Author     : xpylypen
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
        <h1><spring:message code="label.goingloan"/></h1>
        <form method="POST" action="${contextPath}/loan/addloan">
            <table>
              <TR>
                  <TD><spring:message code="label.printedbook"/><INPUT TYPE="TEXT" NAME="pbookid" value="${pbook.idPrintedBook}"/></TD>
                  <TD><spring:message code="label.bookname"/><INPUT TYPE="TEXT" value="${book.name}"></TD>
                  <TD><spring:message code="label.loanto"/><INPUT TYPE="TEXT" name="datetill" class="date"/></TD>
                  <TD><spring:message code="label.member"/><select name="memberid">
                      <c:forEach var="listValue" items="${lmembers}">
                          <option value="${listValue.idMember}">${listValue.name}</option>
                      </c:forEach>
                  </select>
              </TR>
            </table>    
          <input type="submit" value="<spring:message code="label.confirm"/>"/>
        </form>
      </section>
      <%@ include file="footer.jsp" %>
    </div>
    <script src="<c:url value="/resources/js/scale.fix.js" />"></script>
</body>
</html>

