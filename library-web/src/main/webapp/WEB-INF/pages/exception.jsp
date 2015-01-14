<%--
    Document   : exception
    Created on : Dec 18, 2014, 11:18:36 PM
    Author     : xpylypen
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
        <h1><spring:message code="label.opsheader"/></h1>
        <br>

        <p style="color: red">
            <spring:message code="label.ops"/>
        </p>
        <%--<b>${name}</b>:  ${message}--%>
        ${message}

    </section>
    <%@ include file="footer.jsp" %>
</div>
<script src="<c:url value="/resources/js/scale.fix.js" />"></script>
</body>
</html>