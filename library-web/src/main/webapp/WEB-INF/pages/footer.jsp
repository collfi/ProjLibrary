<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>
<footer>
    <p><spring:message code="label.createdby"/></p>

    <p>
        <small>Hosted on GitHub Pages &mdash;<a href="https://github.com/Cospel/ProjLibrary">ProjLibrary</a></small>
    </p>
    <p>
        <small>Theme by <a href="https://github.com/orderedlist">orderedlist</a></small>
    </p>
</footer>
</body>
</html>