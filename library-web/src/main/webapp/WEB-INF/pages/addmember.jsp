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
        <h1><spring:message code="label.addmember"/></h1>
        <c:choose>
            <c:when test="${error == 'missing'}">
                <p><font color="red"><spring:message code="label.validationmissing"/></font></p>
            </c:when>
        </c:choose>
        <c:choose>
            <c:when test="${error == 'duplicate'}">
                <p><font color="red"><spring:message code="label.validationduplicate"/></font></p>
            </c:when>
        </c:choose>
        <c:choose>
            <c:when test="${error == 'email'}">
                <p><font color="red"><spring:message code="label.validationemail"/></font></p>
            </c:when>
        </c:choose>

        <form:form method="POST" action="${contextPath}/member/addpost" modelAttribute="member">
            <table>
                <TR>
                    <TD><spring:message code="label.username"/>:</TD>
                    <td><form:input path="name" value="${name}"/></td>
                </TR>
                <TR>
                    <TD><spring:message code="label.email"/>:</TD>
                    <td><form:input path="email" value="${email}"/></td>
                </TR>
                <TR>
                    <TD><spring:message code="label.address"/>:</TD>
                    <td><form:input path="address" value="${address}"/></td>
                </TR>
                <TR>
                    <TD><spring:message code="label.password"/>:</TD>
                    <td><form:input type="password" path="password" value="${password}"/></td>
                </TR>
                <TR>
                    <TD><spring:message code="label.role"/>:</TD>
                    <td><form:checkbox path="isAdmin"/></td>
                </TR>
            </table>
            <input type="submit" value="<spring:message code='label.submit'/>"/>
        </form:form>
    </section>
    <%@ include file="footer.jsp" %>
</div>
<script src="<c:url value="/resources/js/scale.fix.js" />"></script>
</body>
</html>           