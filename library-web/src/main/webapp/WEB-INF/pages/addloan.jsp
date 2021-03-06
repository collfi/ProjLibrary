<%-- 
    Document   : addloan
    Author     : xpylypen
--%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!doctype html>
<html>

<body>
<div class="wrapper">
    <%@ include file="header.jsp" %>
    <section>
        <h1><spring:message code="label.addloan"/></h1>
        <c:choose>
            <c:when test="${error == 'validationmissing'}">
                <p><font color="red"><spring:message code="label.noselected"/></font></p>
            </c:when>
        </c:choose>
        <c:choose>
            <c:when test="${error == 'nofreebook'}">
                <p><font color="red"><spring:message code="label.nofreebook"/></font></p>
            </c:when>
        </c:choose>
        <c:choose>
            <c:when test="${error == 'wrongdate'}">
                <p><font color="red"><spring:message code="label.wrongdate"/></font></p>
            </c:when>
        </c:choose>
        <form:form method="POST" modelAttribute="search" action="${contextPath}/loan/addloan/member">
            <table>
                <tbody>
                <tr>
                    <td>
                        <spring:message code="label.member"/>
                    </td>
                    <td>
                        <form:select path="search">
                            <c:forEach var="listValue" items="${lmembers}">
                                <form:option value="${listValue.idMember}">${listValue.name}</form:option>
                            </c:forEach>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <spring:message code="label.book"/>
                    </td>
                    <td>
                        <form:select path="book">
                            <c:forEach var="listValue" items="${lbooks}">
                                <form:option value="${listValue.idBook}">${listValue.name}</form:option>
                            </c:forEach>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <spring:message code="label.loanto"/>
                    </td>
                    <td>
                        <input name="dateto" class="date" value="${dateto}"/>
                    </td>
                </tr>
                </tbody>
            </table>
            <input type="submit" value="<spring:message code='label.submit'/>"/>

        </form:form>
    </section>
    <%@ include file="footer.jsp" %>
</div>
<script src="<c:url value="/resources/js/scale.fix.js" />"></script>
</body>
</html>

