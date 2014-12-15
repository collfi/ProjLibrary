<%-- 
    Document   : showbooks
    Created on : Nov 26, 2014, 12:12:50 AM
    Author     : michal
--%>
<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%-- 
    Document   : showbooks
    Created on : Nov 26, 2014, 1:50:29 AM
    Author     : michal
--%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!doctype html>
<html>
<body>
<div class="wrapper">
    <%@ include file="header.jsp" %>
    <section>
        <h1><spring:message code="label.book"/></h1>

        <p>
            <a href="${contextPath}/book/edit/${book.idBook}"><spring:message code="label.editbook"/></a> |
            <a href="${contextPath}/pbook/addformular/${book.idBook}"><spring:message code="label.addpbook"/></a>
        </p>

        <p>
            <form:form modelAttribute="book">
        <table>
            <tbody>
            <tr>

                <td><spring:message code="label.name"/></td>
                <td><form:input readonly="true" path="name" value="${name}"/></td>
            </tr>
            <tr>
                <td>ISBN:</td>
                <td><form:input readonly="true" path="ISBN" value="${isbn}"/></td>
            </tr>
            <tr>
                <td><spring:message code="label.authors"/>:</td>
                <td><form:input readonly="true" path="authors" value="${authors}"/></td>
            </tr>
            <tr>
                <td><spring:message code="label.description"/>:</td>
                <td><form:input readonly="true" path="description" value="${description}"/></td>
            </tr>
            <tr>
                <td><spring:message code="label.genre"/>:</td>
                <td>
                    <form:select readonly="true" path="department">
                        <form:option value="Science">Science</form:option>
                        <form:option value="Sport">Sport</form:option>
                        <form:option value="Autobiografy">Autobiografy</form:option>
                        <form:option value="Religion">Religion</form:option>
                    </form:select>
                </td>
            </tr>

            </tbody>
        </table>
        </form:form>
        <br>

        <h3><spring:message code="label.printedbooks"/>:</h3>
        <table>
            <tbody>
            <tr>
                <th>ID</th>
                <th><spring:message code="label.condition"/></th>
                <th><spring:message code="label.availability"/></th>
                <th><spring:message code="label.action"/></
                >
            </tr>
            <c:forEach var="listValue" items="${list}">
                <tr>
                    <td>${listValue.idPrintedBook}</td>
                    <td><spring:message code="label.${listValue.condition}"/></td>
                    <td><spring:message code="label.${listValue.state}"/></td>
                    <td><a href="${contextPath}/pbook/edit/${listValue.idPrintedBook}">
                        <spring:message code="label.edit"/></a>
                        /<a href="${contextPath}/pbook/delete/${listValue.idPrintedBook}">
                            <spring:message code="label.delete"/></a>

                        /<a href="${contextPath}/loan/addloan?pbookid=${listValue.idPrintedBook}&bookid=${book.idBook}">
                            <spring:message code="label.addloan"/></a>

                    </td>
                </tr>
            </c:forEach><a href="edit/${listValue.idPrintedBook}"></a>
            </tbody>
        </table>
    </section>
    <%@ include file="footer.jsp" %>
</div>


<script src="<c:url value="/resources/js/scale.fix.js" />"></script>
</body>
</html>