<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tara
  Date: 4/29/16
  Time: 5:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="header.jsp">
        <jsp:param name="title" value="Read Comics"/>
        <jsp:param name="css" value="/css/createHome.css"/>
        <jsp:param name="js" value="/js/readComics.js"/>
    </jsp:include>
</head>
<body>
    <div class="outer">
        <div class="jumbotron" style="height: 40vh;">
            <div id="promoteCarousel" class="carousel slide col-sm-9" data-ride="carousel" >

            <div class="carousel-inner" role="listbox" style="overflow: hidden;">
                <%--go to the comicSeries--%>
                <c:forEach var="series" items="${randomSeries}" varStatus="loop">
                    <form action="/read/${series.title}" method="post" id="${series.seriesID}" class="item col-xs-10 col-xs-offset-1 series ${loop.first ? "active" : "" }" style="overflow: hidden;">
                        <img style="height: 90%;" src="${series.seriesCover}"/>
                        <p class="text-center">${series.title}</p>
                        <input type="text" style="display: none" name="seriesID" value="${series.seriesID}"/>
                    </form>
                </c:forEach>
            </div>

            <a class="left carousel-control" href="#promoteCarousel" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"/>
                <span class="sr-only">Previous</span>
            </a>

            <a class="right carousel-control" href="#promoteCarousel" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"/>
                <span class="sr-only">Next</span>
            </a>
        </div>
            <a class="col-sm-3" href="/create"><img src="/img/createPromote.jpg" alt="img promotion goes here" style="height:100%;"/></a>
        </div>

        <%--Newest--%>
        <div class="groupPreview">
            <a href="/read/newReleases" class="viewMore pull-right">
                <i class="fa fa-angle-right fa-lg" aria-hidden="true"></i> View More
            </a>
            <label for="latestSeries">Newest</label>
            <div class="row preview"id="latestSeries">
                <div class="row">
                    <c:forEach  begin="0" end="3"  var="i" items="${latestSeries}">
                        <div class="col-sm-3  item">
                            <form action="/read/${i.title}" method="post" id="${i.seriesID}" class="thumbnail list series" >
                                <div style="height: 85%;">
                                    <img src="${i.seriesCover}" class="seriesImg" style="width:100%;">
                                </div>
                                <div class="caption text-center">
                                    <p>${i.title}</p>
                                </div>
                                <input type="text" style="display: none" name="seriesID" value="${i.seriesID}"/>
                            </form>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

        <%--Popular--%>

        <div class="groupPreview">
            <a href="/read/topComics" class="viewMore pull-right">
                <i class="fa fa-angle-right fa-lg" aria-hidden="true"></i> View More
            </a>
            <label for="popularSeries">Popular</label>
            <div class="row preview"id="popularSeries">
                <div class="row">
                    <c:forEach  begin="0" end="3"  var="i" items="${popularSeries}">
                        <div class="col-sm-3  item">
                            <form action="/read/${i.title}" method="post" id="${i.seriesID}" class="thumbnail list series" >
                                <div style="height: 85%;">
                                    <img src="${i.seriesCover}" class="seriesImg" style="width:100%;">
                                </div>
                                <div class="caption text-center">
                                    <p>${i.title}</p>
                                </div>
                                <input type="text" style="display: none" name="seriesID" value="${i.seriesID}"/>
                            </form>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>


    </div>
</body>
</html>
