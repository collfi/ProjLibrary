<%-- 
    Document   : showbooks
    Created on : Nov 26, 2014, 1:50:29 AM
    Author     : michal
--%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<!doctype html>
<html>
<body>
<div class="wrapper">
    <%@ include file="header.jsp" %>
    <section>
        <h1><spring:message code="label.allbooks"/></h1>
        <table>
            <tbody>
            <tr>
                <th><spring:message code="label.name"/></th>
                <th>ISBN</th>
                <th><spring:message code="label.authors"/></th>
                <th><spring:message code="label.genre"/></th>
                <th><spring:message code="label.show"/>/<spring:message code="label.edit"/>/<spring:message
                        code="label.delete"/></th>
            </tr>
            <c:forEach var="listValue" items="${list}">
                <tr>
                    <td>${listValue.name}</td>
                    <td>${listValue.ISBN}</td>
                    <td>${listValue.authors}</td>
                    <td>${listValue.department}</td>
                    <td><a href="${contextPath}/book/id/${listValue.idBook}"><spring:message code="label.show"/></a>/<a
                            href="${contextPath}/book/edit/${listValue.idBook}"><spring:message code="label.edit"/></a>/<a
                            href="${contextPath}/book/delete/${listValue.idBook}"><spring:message
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
