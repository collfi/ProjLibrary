<%-- 
    Document   : showbooks
    Created on : Nov 26, 2014, 1:50:29 AM
    Author     : michal
--%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<spring:message code="label.name" var="nametext"/>
<spring:message code="label.authors" var="authorstext"/>

<!doctype html>
<html>
<body>
<div class="wrapper">
    <%@ include file="header.jsp" %>
    <section>
        <h1><spring:message code="label.findbooks"/></h1>
        <br>

        <div id="search">
            <form:form method="POST" modelAttribute="search" action="${contextPath}/book/findbooks/result">
                <form:input type="text" path="input"/>
                <form:select path="search">
                    <form:option value="Name"><spring:message code='label.name'/></form:option>
                    <form:option value="Authors"><spring:message code='label.authors'/></form:option>
                    <form:option value="ISBN">ISBN</form:option>
                    <form:option value="Department"><spring:message code='label.genre'/></form:option>
                </form:select>
                <input type="submit" value="<spring:message code='label.submit'/>"/>
            </form:form>
        </div>
        <br>
        <table>
            <tbody>
            <tr>
                <th><spring:message code="label.name"/></th>
                <th>ISBN</th>
                <th><spring:message code="label.authors"/></th>
                <th><spring:message code="label.genre"/></th>
                <th><spring:message code="label.show"/>
                <sec:authorize access="hasRole('ROLE_ADMIN')">/
                    <spring:message code="label.edit"/>/
                    <spring:message code="label.delete"/>
                    </sec:authorize>
                    </th>
            </tr>
            <c:forEach var="listValue" items="${list}">
                <tr>
                    <td>${listValue.name}</td>
                    <td>${listValue.ISBN}</td>
                    <td>${listValue.authors}</td>
                    <td>${listValue.department}</td>
                    <td><a href="${contextPath}/book/id/${listValue.idBook}"><spring:message code="label.show"/></a>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                        /<a
                            href="${contextPath}/book/edit/${listValue.idBook}"><spring:message code="label.edit"/></a>/<a
                            href="${contextPath}/book/delete/${listValue.idBook}"><spring:message
                            code="label.delete"/></a>
                        </sec:authorize>
                    </td>
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
