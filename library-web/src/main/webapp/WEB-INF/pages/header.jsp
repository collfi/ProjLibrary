<%-- 
    Document   : header
    Created on : Nov 23, 2014, 11:18:36 PM
    Author     : michal
--%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="chrome=1">
    <meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">
    <title>Pa165</title>

    <link rel="stylesheet" href="<c:url value="/resources/css/styles.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/pygment_trac.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui.css" />">

    <script src="<c:url value="/resources/js/jquery.js" />"></script>
    <script src="<c:url value="/resources/js/jquery-ui.js" />"></script>
    <script>
        $(document).ready(function () {
            $('#nav > li > a').click(function () {
                if ($(this).attr('class') != 'active') {
                    $('#nav li ul').slideUp();
                    $(this).next().slideToggle();
                    $('#nav li a').removeClass('active');
                    $(this).addClass('active');
                }
            });
            $(".date").datepicker();
        });
    </script>
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
</head>
<body>
<div class="wrapper">
    <header>
        <h1><a href="${contextPath}"><spring:message code="label.appname"/></a></h1>
        <p><spring:message code="label.appdescription"/></p>
        <spring:message code="label.loggedas"/> <b><c:out value="${pageContext.request.remoteUser}"/></b><br>
        <form action="${contextPath}/logout" method="post">
            <input type="submit" value="Logout" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <br>
        <span>
        <a href="?lang=en">en</a> 
        | 
        <a href="?lang=sk">sk</a>
        </span>
        <br>
        <br>
        <ul id="nav">
              
            <li><a href="#"><spring:message code="label.bookmanagement"/></a>
                    
                <ul>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li><a href="${contextPath}/book/addformular"><spring:message code="label.addbook"/></a></li>
                    </sec:authorize>
                    <li><a href="${contextPath}/book/findbooks"><spring:message code="label.findbook"/></a></li>
                    <li><a href="${contextPath}/book/showbooks"><spring:message code="label.allbooks"/></a></li>
                        
                </ul>
                  
            </li>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <li><a href="#"><spring:message code="label.loanmanagement"/></a>
                    <ul>
                        <li><a href="${contextPath}/loan/addloan"><spring:message code="label.addloan"/></a></li>
                        <li><a href="${contextPath}/loan/findloans"><spring:message code="label.findloans"/></a></li>
                        <li><a href="${contextPath}/loan/listloans"><spring:message code="label.allloans"/></a></li>
                    </ul>
                </li>

                <li><a href="#"><spring:message code="label.membermanagement"/></a>
                    <ul>
                        <li><a href="${contextPath}/member/addformular"><spring:message code="label.addmember"/></a></li>
                        <li><a href="${contextPath}/member/findmember"><spring:message code="label.findmember"/></a></li>
                        <li><a href="${contextPath}/member/showmembers"><spring:message code="label.allmembers"/></a></li>
                    </ul>
                </li>
            </sec:authorize>
        </ul>
    </header>
</div>
</body>
</html>
