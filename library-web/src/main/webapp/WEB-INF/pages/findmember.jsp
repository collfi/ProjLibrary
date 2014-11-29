<%-- 
    Document   : showmember
    Created on : Nov 26, 2014, 1:50:29 AM
    Author     : xvalent2
--%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!doctype html>
<html>
  <body>
    <div class="wrapper">
      <%@ include file="header.jsp" %>
             <script>
        $(document).ready(function () {
$('#nav li:first ul').show();
});
    </script>
    <section>
        <h1><spring:message code="label.findmember"/></h1>
        <br>
        <div id="search">
            <form:form method="POST" modelAttribute="search" action="${contextPath}/member/findmember/result">
                <form:input type="text" path="input" />
                <form:select path="search">
                    <form:option value="Name"><spring:message code='label.name'/></form:option>
                    <form:option value="email">email</form:option>
                </form:select>
                <input type="submit" value="Submit"/>
            </form:form>
        </div>
        <br>
        <table>
            <tbody>
            <tr>
                <th><spring:message code="label.name"/></th><th>e-mail</th><th><spring:message code="label.address"/></th><th><spring:message code="label.show"/>/<spring:message code="label.edit"/>/<spring:message code="label.delete"/></th>
            </tr>
            <c:forEach var="listValue" items="${list}">
                <tr>
                    <td>${listValue.name}</td><td>${listValue.email}</td><td>${listValue.address}</td><td><a href="${contextPath}/member/id/${listValue.idMember}">Show</a>/<a href="${contextPath}/member/edit/${listValue.idMember}"><spring:message code="label.edit"/></a>/<a href="${contextPath}/member/delete/${listValue.idMember}"><spring:message code="label.delete"/></a></td>
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