<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="chrome=1">
    
    <title>Pa165</title>

    <link rel="stylesheet" href="<c:url value="/resources/css/styles.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/pygment_trac.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui.css" />">

    <script src="<c:url value="/resources/js/jquery.js" />"></script>
    <script src="<c:url value="/resources/js/jquery-ui.js" />"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
</head>
<body>
    <div class="wrapper">
        <header>
            <h1><a href="${contextPath}"><spring:message code="label.appname"/></a></h1>
            <p><spring:message code="label.appdescription"/></p>
        <span>
        <a href="?lang=en">en</a>
        |
        <a href="?lang=sk">sk</a>
        </span>
            <br>
            <br>

                <form id="form" action="<c:url value='/login.do'/>" method="POST">

                    <c:if test="${not empty param.err}">
                        <div><font color="red"> <spring:message code="label.wronglogin"/> </font></div>
                    </c:if>
                    <c:if test="${not empty param.out}">
                        <div><spring:message code="label.logoutok"/></div>
                    </c:if>
                    <c:if test="${not empty param.time}">
                        <div><spring:message code="label.logoutinactive"/></div>
                    </c:if>

                    <spring:message code="label.email"/>
                    <p><input type="text" name="username" value=""/></p>
                    <spring:message code="label.password"/>
                    <p><input type="password" name="password" value=""/></p>

                    <p><input value="<spring:message code="label.submit"/>" name="submit" type="submit"/></p>
                </form>
        </header>
    <%@ include file="footer.jsp" %>
</div>
<script src="<c:url value="/resources/js/scale.fix.js" />"></script>
</body>
</html>
