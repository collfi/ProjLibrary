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
            $('#nav li:first ul').show();
        });
        </script>
      <section>
        <h1><spring:message code="label.findloans"/></h1>
        <br>
        <div id="search">
            <h2>Find loans by date</h2>
            <form:form method="POST" modelAttribute="search" action="${contextPath}/loan/findloans">
                <spring:message code='label.loanfrom'/>: <input path="loanfrom" class=".date"/>
                <spring:message code='label.loanto'/>: <input path="loanto" class=".date"/>
                <input name="search" value="date" hidden="true"/>
                <input type="submit" value="Submit"/>
            </form:form>
        </div>
        <br>
        <table>
            <tbody>
            <tr>
                <th><spring:message code="label.loanid"/></th>
                <th><spring:message code="label.loanmember"/></th>
                <th><spring:message code="label.loanbooks"/></th>
                <th><spring:message code="label.loanfrom"/></th>
                <th><spring:message code="label.loanto"/></th>
                <th><spring:message code="label.loandatereturned"/></th>
                <th></th>
            </tr>
            <c:forEach var="loan" items="${list}">
                <tr>
                    <td>${loan.idLoan}</td>
                    <td>${loan.member.name}</td>
                    <td><c:forEach var="pbook" items="${loan.pbooks}">
                        ${pbook.idPrintedBook}, 
                    </c:forEach></td>
                    <td><fmt:formatDate value="${loan.fromDate}" pattern="yyyy-MM-dd" /></td>
                    <td><fmt:formatDate value="${loan.toDate}" pattern="yyyy-MM-dd" /></td>
                    <td>${loan.dateReturned}</td>
                    <td><a href="${contextPath}/loan/delete/${loan.idLoan}"><spring:message code="label.delete"/></a></td>
                </tr>
                <tr>
                    <td>${loan.description}</td>
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
