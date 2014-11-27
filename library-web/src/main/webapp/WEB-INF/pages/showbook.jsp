<%-- 
    Document   : showbooks
    Created on : Nov 26, 2014, 12:12:50 AM
    Author     : michal
--%>
<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%-- 
    Document   : showbooks
    Created on : Nov 26, 2014, 1:50:29 AM
    Author     : michal
--%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


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
        <h1><spring:message code="label.book"/></h1></br>
        <p>  
            <a href="${contextPath}/book/edit/${book.idBook}"><spring:message code="label.editbook"/></a> |
            <a href="${contextPath}/pbook/addformular/${book.idBook}"><spring:message code="label.addpbook"/></a>
            <p><spring:message code="label.name"/> : ${book.name}</p>
            <p>ISBN : ${book.ISBN}</p>
            <p><spring:message code="label.authors"/> : ${book.authors}</p>
            <p><spring:message code="label.description"/> : ${book.description}</p>
            <p><spring:message code="label.genre"/> : ${book.department}</p>
        </p></br>
        <h3><spring:message code="label.printedbooks"/>:</h3>
        <table>
         <tbody>
            <tr>
                <th>ID</th><th><spring:message code="label.condition"/></th><th><spring:message code="label.state"/></th><th><spring:message code="label.action"/></>
            </tr>
         <c:forEach var="listValue" items="${list}">
                <tr>
                    <td>${listValue.idPrintedBook}</td><td><spring:message code="label.${listValue.condition}"/></td><td><spring:message code="label.${listValue.state}"/></td><td><a href="${contextPath}/pbook/edit/${listValue.idPrintedBook}"><spring:message code="label.edit"/></a>/<a href="${contextPath}/pbook/delete/${listValue.idPrintedBook}"><spring:message code="label.delete"/></a></td>
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