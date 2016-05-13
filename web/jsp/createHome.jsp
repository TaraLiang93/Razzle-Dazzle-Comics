<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tara
  Date: 4/29/16
  Time: 1:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="header.jsp">
        <jsp:param name="title" value="Creation"/>
        <jsp:param name="css" value="/css/createHome.css"/>
        <jsp:param name="js" value="/js/ideaFactory.js"/>
    </jsp:include>

    <script src="/js/projectAdminPage.js"></script>
</head>
<div class="outer">
    <div class="jumbotron row" style="height: 150px; width: 950px; margin: 0 auto;">
        <a href="/read"><img src="/img/ReadPromote.jpg" alt="img promotion goes here"/></a>
    </div>


    <div class="groupPreview">
        <a href="/create/projectAdminPage" class="viewMore pull-right">
            <i class="fa fa-angle-right fa-lg" aria-hidden="true"></i> View More
        </a>
        <label for="seriespreview">Series</label>
        <div class="row preview"id="seriespreview">
            <div class="row">
                <c:forEach  begin="0" end="3"  var="i" items="${series}">
                    <div class="col-sm-3  item">
                        <div id="${i.seriesID}" class="thumbnail list series" >
                            <div style="height: 85%;">
                                <img src="${i.seriesCover}" class="seriesImg" style="width:100%;">
                            </div>
                            <div class="caption text-center">
                                <p>${i.title}</p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>


    <div class="groupPreview">
        <label for="doodlesPreview" >Doodles</label>
        <a href="/create/ideas#Doodles" class="viewMore pull-right">
            <i class="fa fa-angle-right fa-lg" aria-hidden="true"></i> View More
        </a>
        <div class="row preview"id="doodlesPreview">
            <div class="row">
                <c:forEach begin="0" end="3" var="i" items="${doodles}">
                    <div class="span2">
                        <div class="col-sm-3 col-md-3">
                            <div id="${i.doodleId}" class="thumbnail list doodle">
                                <img class="idea" src="/img/logo.jpg">
                                <div class="caption">
                                    <h3>${i.title}</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>


    <div class="groupPreview">
        <a href="/create/ideas#Scribbles" class="viewMore pull-right">
            <i class="fa fa-angle-right fa-lg" aria-hidden="true"></i> View More
        </a>
        <label for="scribblePreview">Scribbles</label>
        <div class="row preview"id="scribblePreview">
            <div class="row">
                <c:forEach begin="0" end="3" var="i" items="${scribbles}">
                    <div class="span2">
                        <div class="col-sm-3 col-md-3">
                            <div id="${i.scribbleId}" class="thumbnail list scribble">
                                <img class="idea" src="/img/logo.jpg">
                                <div class="caption">
                                    <h3>${i.title}</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>

</div>
</body>
</html>
