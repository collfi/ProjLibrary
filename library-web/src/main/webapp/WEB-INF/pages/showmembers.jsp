<%-- 
    Document   : book
    Created on : Nov 23, 2014, 11:18:36 PM
    Author     : michal
--%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<!doctype html>
<html>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<body>
<div class="wrapper">
    <%@ include file="header.jsp" %>
    <section>
        <h1><spring:message code="label.allmembers"/></h1>
        <table>
            <tbody>
            <tr>
                <th><spring:message code="label.name"/></th>
                <th><spring:message code="label.email"/></th>
                <th><spring:message code="label.address"/></th>
                <th><spring:message code="label.show"/>/<spring:message code="label.edit"/>/<spring:message
                        code="label.delete"/></th>
            </tr>
            <c:forEach var="listValue" items="${list}">
                <tr>
                    <td>${listValue.name}</td>
                    <td>${listValue.email}</td>
                    <td>${listValue.address}</td>
                    <td><a href="${contextPath}/member/id/${listValue.idMember}"><spring:message code="label.show"/></a>/<a
                            href="${contextPath}/member/edit/${listValue.idMember}"><spring:message
                            code="label.edit"/></a>/<a
                            href="${contextPath}/member/delete/${listValue.idMember}"><spring:message
                            code="label.delete"/></a></td>
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