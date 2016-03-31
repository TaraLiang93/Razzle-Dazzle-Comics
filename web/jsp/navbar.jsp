<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tara
  Date: 3/29/16
  Time: 9:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${param.title}</title>

    <script src="http://code.jquery.com/jquery-1.12.0.min.js"></script>
    <script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>


    <%--<jsp:include page="header.jsp">--%>
        <%--<jsp:param name="title" value="Let's Play"/>--%>
        <%--<jsp:param name="js" value="/js/play.js"/>--%>
    <%--</jsp:include>--%>

    <c:forEach items = "${param.js}" var = "file">
        <c:if test="${not empty file.js}">
            <script src="${file.js}"></script>
        </c:if>
    </c:forEach>

    <c:forEach items = "${param.css}" var = "style">
        <c:if test="${not empty style}">
            <link rel = "stylesheet" href="${style}">
        </c:if>
    </c:forEach>

</head>
<body>
    <div class = "container-fluid">
        <nav class = "header">
            <div class = "col-sm-2"></div>
            <div class="col-sm-10">
                <div class="col-sm-8">
                <a href src = "./"><img src= "logo.jpg" height = '50'></a>
                <a href src = "./">READ COMIC</a>
                <a href src = "./">CREATE COMIC</a>
                    </div>
                <div class="col-sm-2">
                    <a class = "signin" href src = "./">Sign In </a>
                </div>
            </div>

        </nav>
    </div>
    <div class = "container-fluid">
        <nav class = "read">
            <div class = "col-xs-2"></div>
            <div class="col-xs-8">
                <a href src = "./">READ COMIC</a>
                <a href src = "./">CREATE COMIC</a>
            </div>
        </nav>
    </div>
</body>
</html>
