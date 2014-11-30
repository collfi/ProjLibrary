<%-- 
    Document   : findloans
    Author     : xpylypen
--%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<spring:message code="label.name" var="nametext"/>
<spring:message code="label.authors" var="authorstext"/>

<!doctype html>
<html>
  <body>
    <div class="wrapper">
      <%@ include file="header.jsp" %>
        <script>
        $(document).ready(function () {
            $('#nav li:second ul').show();
        });
        </script>
      <section>
        <h1><spring:message code="label.findloans"/></h1>
        <br>
        <div id="search">
            <h1>Find</h1>
            <form:form method="POST" modelAttribute="search" action="${contextPath}/loan/findloans/result">
                <form:input type="text" path="input" />
                <form:select path="search">
                    <form:option value="Member"><spring:message code='label.member'/></form:option>
                    <form:option value="Id">Id</form:option>
                    <form:option value="Book"><spring:message code='label.book'/></form:option>
                </form:select>
                <input type="submit" value="Submit"/>
            </form:form>
        </div>

        <table>
            <tbody>
            <tr>
                <th><spring:message code="label.loanid"/></th>
                <th><spring:message code="label.loanmember"/></th>
                <th><spring:message code="label.loanbooks"/></th>
                <th><spring:message code="label.loanfrom"/></th>
                <th><spring:message code="label.loanto"/></th>
                <th><spring:message code="label.loandatereturned"/></th>
            </tr>
            <c:forEach var="loan" items="${loans}">
                <tr>
                    <td>${loan.idLoan}</td>
                    <td>${loan.member.name}</td>
                    <td><fmt:formatDate value="${loan.fromDate}" pattern="yyyy-MM-dd" /></td>
                    <td><fmt:formatDate value="${loan.toDate}" pattern="yyyy-MM-dd" /></td>
                    <td>${loan.dateReturned}</td>
                    <td><a href="${contextPath}/loan/delete/${loan.idLoan}"><spring:message code="label.delete"/></a>/<a href="${contextPath}/loan/setreturned/${loan.idLoan}"><spring:message code="label.setreturned"/></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
      </section>
      <%@ include file="footer.jsp" %>
    </div>
    <script src="<c:url value="/resources/js/scale.fix.js" />"></script>
</body>
</html>
