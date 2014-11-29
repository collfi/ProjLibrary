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
            <h2><spring:message code="label.findByDate"/></h2>
            <form:form method="POST" modelAttribute="search" action="${contextPath}/loan/findloans/date">
                <spring:message code='label.loanfrom'/>:
                <input name="datefrom" class="date" value="${datefrom}"/>
                <spring:message code='label.loanto'/>:
                <input name="dateto" class="date" value="${dateto}"/>
                <input type="submit" value="<spring:message code="label.search"/>"/>
            </form:form>
            <h2><spring:message code="label.findByMember"/></h2>
            <form:form method="POST" modelAttribute="search" action="${contextPath}/loan/findloans/member">
                <select name="memberid">
                    <c:forEach var="listValue" items="${lmembers}">
                        <option <c:if test="${listValue.idMember == memberid}">selected</c:if>
                                value="${listValue.idMember}">${listValue.name}</option>
                    </c:forEach>
                </select>
                <select name="returned">
                    <option selected value="true"><spring:message code='label.yes'/></option>
                    <option selected value="false"><spring:message code='label.no'/></option>
                </select>
                <input type="submit" value="<spring:message code="label.search"/>"/>
            </form:form>
            <h2><spring:message code="label.findByBook"/></h2>
            <form:form method="POST" modelAttribute="search" action="${contextPath}/loan/findloans/book">
                <select name="bookid">
                    <c:forEach var="listValue" items="${books}">
                        <option <c:if test="${listValue.idBook == bookid}">selected</c:if>
                                value="${listValue.idBook}">${listValue.name}</option>
                    </c:forEach>
                </select>
                <input type="submit" value="<spring:message code="label.search"/>"/>
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
            <c:forEach var="loan" items="${loans}">
                <tr>
                    <td>${loan.idLoan}</td>
                    <td>${loan.member.name}</td>
                    <td><c:forEach var="pbook" items="${loan.pbooks}">
                        ${pbook.idPrintedBook}, 
                    </c:forEach></td>
                    <td><fmt:formatDate value="${loan.fromDate}" pattern="yyyy-MM-dd" /></td>
                    <td><fmt:formatDate value="${loan.toDate}" pattern="yyyy-MM-dd" /></td>
                    <td>${loan.dateReturned}</td>
                    <td><a href="${contextPath}/loan/delete/${loan.idLoan}"><spring:message code="label.delete"/></a>/
                        <a href="${contextPath}/loan/setreturned/${loan.idLoan}"><spring:message code="label.setreturned"/></a></td>
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
