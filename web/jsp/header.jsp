<%--
  Created by IntelliJ IDEA.
  User: tara
  Date: 3/29/16
  Time: 9:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <link rel="stylesheet" href="/css/navbar.css">

    <c:if test="${not empty param.js}">
        <c:forEach items = "${param.js}" var = "file">
                <script src="${file.js}"></script>
        </c:forEach>
    </c:if>

    <c:if test="${not empty param.css}">
        <c:forEach items = "${param.css}" var = "style">
                <link rel = "stylesheet" href="${style}">
        </c:forEach>
    </c:if>


</head>
<body>

    <header>
        <div class = "container-fluid header">
            <nav class = "navbar">
                <div >
                    <div class="pull-left side">
                        <a href src = "#"><img src= "/logo.jpg" height = '50'></a>
                        <a href src = "#">READ COMIC</a>
                        <a href src = "#">CREATE COMIC</a>
                    </div>
                    <div class="pull-right navright side">
                        <a id="signin" class="accountSettings" href src = "#">Sign In</a>
                    </div>
                </div>

            </nav>
        </div>
    </header>
