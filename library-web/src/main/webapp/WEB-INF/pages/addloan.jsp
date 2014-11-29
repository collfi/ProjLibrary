<%-- 
    Document   : addloan
    Author     : xpylypen
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
      <section>
        <h1><spring:message code="label.goingloan"/></h1>
            <form:form method="POST" modelAttribute="search" action="${contextPath}/loan/addloan/member">
                <form:select path="search">
                    <c:forEach var="listValue" items="${lmembers}">
                        <form:option value="${listValue.idMember}">${listValue.name}</form:option>
                    </c:forEach>
                </form:select>
                <form:select path="book">
                    <c:forEach var="listValue" items="${lbooks}">
                        <form:option value="${listValue.idBook}">${listValue.name}</form:option>
                    </c:forEach>
                </form:select>
                <input type="submit" value="Submit"/>
            </form:form>
      </section>
      <%@ include file="footer.jsp" %>
    </div>
    <script src="<c:url value="/resources/js/scale.fix.js" />"></script>
</body>
</html>

