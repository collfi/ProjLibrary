<%-- 
    Document   : book
    Created on : Nov 23, 2014, 11:18:36 PM
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
        <h1><spring:message code="label.welcome"/></h1>
        <br>

        <p>
            <spring:message code="label.maincontent1"/>
        </p>

        <p>
            <spring:message code="label.maincontent2"/>
        </p>

    </section>
    <%@ include file="footer.jsp" %>
</div>
<script src="<c:url value="/resources/js/scale.fix.js" />"></script>
</body>
</html>