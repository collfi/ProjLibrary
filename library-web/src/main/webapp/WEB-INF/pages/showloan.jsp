<%-- 
    Document   : showbooks
    Created on : Nov 26, 2014, 12:12:50 AM
    Author     : xvalent2
--%>
<!DOCTYPE html>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


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
                <th><spring:message code="label.loanid"/></th>
                <th><spring:message code="label.loanmember"/></th>
                <th><spring:message code="label.loanbooks"/></th>
                <th><spring:message code="label.loanfrom"/></th>
                <th><spring:message code="label.loanto"/></th>
                <th><spring:message code="label.loandatereturned"/></th>
                <th></th>
            </tr>
            <tr>
                <td>${loan.idLoan}</td>
                <td>${loan.member.name}</td>
                <td>${loan.printedBook.book.name}</td>
                <td><fmt:formatDate value="${loan.fromDate}" pattern="yyyy-MM-dd"/></td>
                <td><fmt:formatDate value="${loan.toDate}" pattern="yyyy-MM-dd"/></td>
                <td><fmt:formatDate value="${loan.dateReturned}" pattern="yyyy-MM-dd"/></td>
                <td><a href="${contextPath}/loan/delete/${loan.idLoan}"><spring:message code="label.delete"/></a>/
                    <a href="${contextPath}/loan/setreturned/${loan.idLoan}"><spring:message
                            code="label.setreturned"/></a></td>
            </tr>
            </tbody>
        </table>

    </section>
    <%@ include file="footer.jsp" %>
</div>


<script src="<c:url value="/resources/js/scale.fix.js" />"></script>
</body>
</html>