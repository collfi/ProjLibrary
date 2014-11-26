<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
 

<html>
<body>
	<h1><spring:message code="label.appname"/></h1>
        <span >
        <a href="?lang=en">en</a> 
        | 
        <a href="?lang=sk">sk</a>
        </span>
        <ul>
            <li><a href="book/management"><spring:message code="label.bookmanagement"/></a></li>
            <li><a href="pbook"><spring:message code="label.pbookmanagement"/></a></li>
            <li><a href=""><spring:message code="label.loanmanagement"/></a></li>
            <li><a href=""><spring:message code="label.membermanagement"/></a></li>
        </ul>
</body>
</html>