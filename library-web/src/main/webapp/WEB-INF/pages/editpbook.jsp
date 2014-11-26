<%-- 
    Document   : editpbook
    Created on : Nov 26, 2014, 4:36:30 PM
    Author     : Boris Valentovic - xvalent2
--%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<!doctype html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="chrome=1">
    <title>Pa165</title>
    
    <link rel="stylesheet" href="<c:url value="/resources/css/styles.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/pygment_trac.css" />">
    <script src="<c:url value="/resources/js/jquery.js" />"></script>
    <script>
        $(document).ready(function () {
  $('#nav > li > a').click(function(){
    if ($(this).attr('class') != 'active'){
      $('#nav li ul').slideUp();
      $(this).next().slideToggle();
      $('#nav li a').removeClass('active');
      $(this).addClass('active');
    }
  });
$('#nav li:first ul').show();
});
    </script>
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
  </head>
  <body>
    <div class="wrapper">
      <header>
        <h1><a href="${contextPath}"><spring:message code="label.appname"/></a></h1>
        <p><spring:message code="label.appdescription"/></p>
        <span >
        <a href="?lang=en">en</a> 
        | 
        <a href="?lang=sk">sk</a>
        </span>
        <br>
       
        <ul id="nav">
          <li><a href="#"><spring:message code="label.bookmanagement"/></a>
            <ul>
            <li><a href="${contextPath}/book/addformular"><spring:message code="label.addbook"/></a></li>
            <li><a href="#"><spring:message code="label.findbook"/></a></li>
            <li><a href="${contextPath}/book/showbooks"><spring:message code="label.allbooks"/></a></li>
            </ul>
          </li>
          <li><a href="#"><spring:message code="label.loanmanagement"/></a>
            <ul>
                <li><a href="#">Sub-Item 3 a</a></li>
                <li><a href="#">Sub-Item 3 b</a></li>
                <li><a href="#">Sub-Item 3 c</a></li>
                <li><a href="#">Sub-Item 3 d</a></li>
            </ul>
          </li>
          <li><a href="#"><spring:message code="label.membermanagement"/></a>
            <ul>
                <li><a href="#">Sub-Item 4 a</a></li>
                <li><a href="#">Sub-Item 4 b</a></li>
                <li><a href="#">Sub-Item 4 c</a></li>
            </ul>
         </li>
        </ul>
      </header>
      <section>
        <h1><spring:message code="label.editpbookcondition"/></h1>
<form method="POST" action="${contextPath}/pbook/editpost">
  <table>
    <TR>
        <TD><INPUT TYPE="TEXT" NAME="idPrintedBook" value="${pbook.idPrintedBook}" hidden="true"></TD>
    </TR>
    <TR>
         <select name="condition">
            <option value="New"><spring:message code="label.new"/></option>
            <option value="Used"><spring:message code="label.used"/></option>
            <option value="Damaged"><spring:message code="label.damaged"/></option>
        </select>
    </TR>
  </table>    
  <input type="submit" value="Submit"/>
</form>
      </section>
      <footer>
        <p>This project is created by Martin Malik, Michal Lukac, Boris Valentovic and Sergii Pylypenko</p>
        <p><small>Hosted on GitHub Pages &mdash;<a href="https://github.com/Cospel/ProjLibrary">ProjLibrary</a></small></p>
        <p><small>Theme by <a href="https://github.com/orderedlist">orderedlist</a></small></p>
      </footer>
    </div>
    <script src="<c:url value="/resources/js/scale.fix.js" />"></script>
</body>
</html>

