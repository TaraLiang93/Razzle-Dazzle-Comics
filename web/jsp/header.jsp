<%--
  Created by IntelliJ IDEA.
  User: tara
  Date: 3/29/16
  Time: 9:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>${param.title}</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

    <script src="http://code.jquery.com/jquery-1.12.0.min.js"></script>
    <script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>

    <link rel="stylesheet" href="/css/navbar.css">
    <link rel="stylesheet" href="/css/customnav.css">
    <link rel="stylesheet" href="/css/customsubnav.css">
    <link rel="stylesheet" href="/css/commonStyles.css">
    <script src="/js/navbar.js"></script>
    <script src="/js/globals.js"></script>


    <c:if test="${not empty param.js}">
        <c:forEach items = "${param.js}" var = "file">
                <script src="${file}"></script>
        </c:forEach>
    </c:if>

    <c:if test="${not empty param.css}">
        <c:forEach items = "${param.css}" var = "style">
                <link rel = "stylesheet" href="${style}">
        </c:forEach>
    </c:if>


    <c:if test="${globals.status eq 'create'}">
            <div id="isAuth" style="display: none"><c:out value="${globals.isAuth}" /></div>
    </c:if>

</head>
<body>

    <header id="navtop">
        <nav class="navbar navbar-default header">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="/"><img id="logo" src= "/img/logo.jpg"/></a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav navlinks">

                        <li class="dropdown ${globals.status == 'read'? "active": none}">
                            <span class="sr-only">(current)</span>
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Read Comics</a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Top 100</a></li>
                                <li><a href="#">Genres</a></li>
                                <li><a href="#">Latest Release</a></li>
                               <c:if test="${true}"><li><a href="#">Bookmarked</a></li></c:if>
                            </ul>
                        </li>
                        <li class="dropdown ${globals.status == 'create'? "active": none}">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Create Comics</a>
                            <ul class="dropdown-menu">
                                <li><a href="/create/ideas">Idea Factory</a></li>
                                <li><a href="/create/projectAdminPage">Project Admin</a></li>
                                <li><a href="#">Upload</a></li>
                            </ul>
                        </li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="${loginURL ne null ? loginURL : "#"}" class="dropdown-toggle" data-toggle="${loginURL eq null ? "dropdown" : "" }" role="button" aria-haspopup="true" aria-expanded="false">${loginURL eq null ? userData.nickName : "Sign In" }<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Profile</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="${logoutURL ne null ? logoutURL : "#"}">Logout</a></li>
                            </ul>
                        </li>

                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.queryContainer-fluid -->
        </nav>

        <c:choose>
            <c:when test="${globals.status == 'read'}">
                <jsp:include page="readNavbar.jsp"/>
            </c:when>
            <c:when test="${globals.status == 'create'}">
                <jsp:include page="writeNavbar.jsp"/>
            </c:when>
            <%--<c:otherwise>--%>
                <%--<c:out value="Error loding the sub navbar!"/>--%>
            <%--</c:otherwise>--%>
        </c:choose>
    </header>

    <c:if test="${not empty param.subtitle}">
        <div class="subtitle center-block">
            <div class="page-header">
                <h1>${param.subtitle}</h1>
            </div>
        </div>
    </c:if>