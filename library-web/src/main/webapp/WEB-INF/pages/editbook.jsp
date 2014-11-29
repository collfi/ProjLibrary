<%-- 
    Document   : book
    Created on : Nov 23, 2014, 11:18:36 PM
    Author     : michal
--%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<!doctype html>
<html>
  <body>
    <div class="wrapper">
      <%@ include file="header.jsp" %>
      <section>
        <h1><spring:message code="label.editbook"/></h1>
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
    <form:form method="POST" action="${contextPath}/book/editpost" modelAttribute="book">
    <table>
    <tbody>
    <tr style="display: none;">
        <TD>Id:</TD>
        <TD><form:input path="idBook" value="${idBook}"/></TD>
    </tr>

    <tr>
        <td><spring:message code="label.name"/></td>
        <td><form:input path="name" value="${name}"/></td>
    </tr>
    <tr>
        <td>ISBN:</td>
        <td><form:input path="ISBN" value="${isbn}"/></td>
    </tr>
    <tr>
        <td><spring:message code="label.authors"/>:</td>
        <td><form:input path="authors" value="${authors}"/></td>
    </tr>
    <tr>
        <td><spring:message code="label.description"/>:</td>
        <td><form:input path="description" value="${description}"/></td>
    </tr>
    <tr>
        <td><spring:message code="label.genre"/>:</td>
        <td>
            <form:select path="department">
                <form:option value="Science">Science</form:option>
                <form:option value="Sport">Sport</form:option>
                <form:option value="Autobiografy">Autobiografy</form:option>
                <form:option value="Religion">Religion</form:option>
            </form:select>
        </td>
    </tr>
    </tbody>
</table>
<input type="submit" value="Submit"/>
</form:form>
      </section>
      <%@ include file="footer.jsp" %>
    </div>
    <script src="<c:url value="/resources/js/scale.fix.js" />"></script>
</body>
</html>
