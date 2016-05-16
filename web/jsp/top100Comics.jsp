<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Jason
  Date: 5/14/16
  Time: 3:42 PM
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Top 100"/>
    <jsp:param name="css" value="/css/top100Comics.css"/>
    <jsp:param name="js" value="/js/top100Comics.js"/>
</jsp:include>


<jsp:include page="filterBoxes.jsp" />

<div class="top100Comics">
    <div class="topComic col-xs-4 filter">
        <form action="/read/${topSeries.title}" method="post" class="topComic topComics col-xs-12 " id="${topSeries.seriesID}">
            <c:forEach var="genre" items="${topSeries.genres}">
                <div style="display: none" class="${genre.name}"></div>
            </c:forEach>
            <input type="text" name="seriesID" style="display: none" value="${topSeries.seriesID}"/>
            <div class="content-border text-center">
                <img class="chapterImg" src="${topSeries.seriesCover}"/>
            </div>
            <p class="text-center chapterTitle">${topSeries.title}</p>
            <p class="text-center views">${topSeries.views} views</p>
        </form>
    </div>

    <div class="comicsList col-xs-8">
        <c:forEach var="s" items="${series}">
            <form action="/read/${s.title}" method="post" class="topComics col-xs-4 filter" style="margin-bottom: 1%;">
                <c:forEach var="genre" items="${s.genres}">
                    <div style="display: none" class="${genre.name}"></div>
                </c:forEach>
                <input type="text" name="seriesID" style="display: none" value="${s.seriesID}"/>
                <div class="content-border text-center">
                    <img class="chapterImg" src="${s.seriesCover}"/>
                </div>
                <p class="text-center chapterTitle" style="margin-bottom: 0;">${s.title}</p>
                <p class="text-center views">${s.views} views</p>
            </form>
        </c:forEach>
    </div>

</div>

<jsp:include page="footer.jsp"/>