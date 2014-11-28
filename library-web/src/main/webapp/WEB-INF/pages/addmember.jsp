<%-- 
    Document   : book
    Created on : Nov 23, 2014, 11:18:36 PM
    Author     : michal
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
        </header>
    <section>
    <form method="POST" action="addpost">    
        <h1><spring:message code="label.addmember"/></h1>
        <form method="POST" action="addpost">
  
        <table>
            <TR>
                <TD><spring:message code="label.name"/>:</TD>
                <TD><INPUT TYPE="TEXT" NAME="name" SIZE="25"></TD>
            </TR>
            <TR>
            <TD><spring:message code="label.email"/>:</TD>
            <TD><INPUT TYPE="TEXT" NAME="email" SIZE="25"></TD>
            </TR>
            <TR>
                <TD><spring:message code="label.address"/>:</TD>
                <TD><INPUT TYPE="TEXT" NAME="address" SIZE="25"></TD>
            </TR>
        </table>    
        
        <input type="submit" value="Submit"/>
        </form>
       
    </section>
    </div>
    <script src="<c:url value="/resources/js/scale.fix.js" />"></script>
</body>
</html>