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

        <h1><spring:message code="label.editmember"/></h1>

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

        <form method="POST" action="${contextPath}/member/editpost">
            <table>
                <TR style="display: none;">
                    <TD>ID</TD>
                    <TD><INPUT TYPE="TEXT" NAME="idMember" value="${member.idMember}" SIZE="25"></TD>
                </TR>
                <TR>
                    <TD><spring:message code="label.name"/>:</TD>
                    <TD><INPUT TYPE="TEXT" NAME="name" value="${member.name}" SIZE="25"></TD>
                </TR>
                <TR>
                    <TD><spring:message code="label.email"/>:</TD>
                    <TD><INPUT TYPE="TEXT" NAME="email" value="${member.email}" SIZE="25"></TD>
                </TR>
                <TR>
                    <TD><spring:message code="label.address"/>:</TD>
                    <TD><INPUT TYPE="TEXT" NAME="address" value="${member.address}" SIZE="25"></TD>
                </TR>
                <TR>
                    <TD><spring:message code="label.password"/>:</TD>
                    <TD><INPUT TYPE="PASSWORD" NAME="password" value="${member.password}" SIZE="25"></TD>
                </TR>
            </table>
            <input type="submit" value="<spring:message code='label.submit'/>"/>
        </form>
    </section>
    <%@ include file="footer.jsp" %>
</div>
<script src="<c:url value="/resources/js/scale.fix.js" />"></script>
</body>
</html>